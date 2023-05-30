package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private Employee mockedTestEmployee;
    @Mock
    private EmployeeService service;

    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    public void setup() {
        mockedTestEmployee = new Employee(1L, "John Doe", 100, "Test Department");
    }

    @Test
    public void getAllEmployeesShouldDelegateCall() {
        controller.getEmployees();
        verify(service).getAll();
    }

    @Test
    public void getEmployeeByIdShouldDelegateCall() {
        final long testEmployeeId = mockedTestEmployee.getId();
        controller.getEmployee(testEmployeeId);
        verify(service).getById(testEmployeeId);
    }

    @Test
    public void saveEmployeeShouldDelegateCall() {
        final Employee testEmployee = mockedTestEmployee;
        controller.saveEmployee(testEmployee);
        verify(service).save(testEmployee);
    }

    @Test
    public void deleteEmployeeShouldDelegateCall() {
        final long testEmployeeId = mockedTestEmployee.getId();
        controller.deleteEmployee(testEmployeeId);
        verify(service).delete(testEmployeeId);
    }

    @Test
    public void updateEmployeeShouldDelegateCallWhenIdExist() {
        final long testEmployeeId = mockedTestEmployee.getId();
        final Employee testEmployee = mockedTestEmployee;
        when(service.getById(testEmployeeId)).thenReturn(new Employee());

        controller.updateEmployee(testEmployee, testEmployeeId);
        verify(service).update(testEmployee);
    }

    @Test
    public void updateEmployeeShouldNotDelegateCallWhenIdDoesNotExist() {
        final long testEmployeeId = -99;
        final Employee testEmployee = mockedTestEmployee;
        when(service.getById(testEmployeeId)).thenReturn(null);

        controller.updateEmployee(testEmployee, testEmployeeId);
        verify(service, never()).update(testEmployee);
    }

    @Test
    public void updateEmployeeShouldDelegateCallWithFixedIdWhenIdExist() {
        final long testEmployeeId = 2;
        final Employee testEmployee = mockedTestEmployee;
        when(service.getById(testEmployeeId)).thenReturn(new Employee());

        Assertions.assertNotEquals(testEmployeeId, testEmployee.getId());
        controller.updateEmployee(testEmployee, testEmployeeId);
        verify(service).update(testEmployee);
        Assertions.assertEquals(testEmployeeId, testEmployee.getId());
    }
}