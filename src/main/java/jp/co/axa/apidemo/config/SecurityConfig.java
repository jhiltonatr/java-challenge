package jp.co.axa.apidemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain web(HttpSecurity http, APIKeyAuthFilter authFilter) throws Exception {
        http
            .csrf().disable()
            .cors().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests((authorize) ->
                authorize
                    .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").authenticated()
                    .anyRequest().denyAll()
            )
            .addFilter(authFilter);

        return http.build();
    }

    @Bean
    public APIKeyAuthFilter authFilter(@Value("${auth.token-header-name}") String principalRequestHeader,
                                @Value("${auth.secret-token}") String principalRequestValue) {

        APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!principalRequestValue.equals(principal)) {
                throw new BadCredentialsException("The API key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        return filter;
    }

    class APIKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

        private String principalRequestHeader;

        public APIKeyAuthFilter(String principalRequestHeader) {
            this.principalRequestHeader = principalRequestHeader;
        }

        @Override
        protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
            return request.getHeader(principalRequestHeader);
        }

        @Override
        protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
            return "N/A";
        }
    }
}
