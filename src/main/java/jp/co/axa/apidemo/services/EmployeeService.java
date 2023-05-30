package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Employee getById(Long employeeId);

    Employee save(Employee employee);

    void delete(Long employeeId);

    Employee update(Employee employee);
}