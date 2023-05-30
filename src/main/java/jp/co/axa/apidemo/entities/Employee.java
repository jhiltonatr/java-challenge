package jp.co.axa.apidemo.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="EMPLOYEES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="EMPLOYEE_NAME")
    @NotNull
    @NotBlank
    private String name;

    @Column(name="EMPLOYEE_SALARY")
    @NotNull
    @Min(0)
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_DEPARTMENT_ID")
    private Department department;

}
