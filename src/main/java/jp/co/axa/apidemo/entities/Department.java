package jp.co.axa.apidemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="DEPARTMENTS")
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name="DEPARTMENT_NAME")
    @Getter
    @Setter
    private String name;

    @OneToMany
    @JoinColumn(name = "id") // we need to duplicate the physical information
    @JsonIgnore
    private transient Set<Employee> items;

}
