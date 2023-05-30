package jp.co.axa.apidemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class ApiDemoApplicationTests {

	@Test
	public void contextLoads(ApplicationContext context) {
		assertNotNull(context);
	}

}
