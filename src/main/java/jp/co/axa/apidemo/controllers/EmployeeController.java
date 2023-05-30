package jp.co.axa.apidemo.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/employees")
@SecurityRequirement(name = "bearer-key")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.getById(employeeId);
    }

    @PostMapping
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.delete(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@Valid @RequestBody Employee employee,
                               @PathVariable(name = "employeeId") Long employeeId) {
        Employee emp = employeeService.getById(employeeId);
        if (Objects.nonNull(emp)) {
            // Prevent discrepancies between path variable and request body
            employee.setId(employeeId);
            return employeeService.update(employee);
        }
        return null;
    }

}
