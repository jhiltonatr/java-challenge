package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private Employee mockedTestEmployee;
    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @BeforeEach
    public void setup() {
        mockedTestEmployee = new Employee(1L, "John Doe", 100, "Test Department");
    }

    @Test
    public void testGetAllShouldDelegateCallToRepository() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    public void testGetByIdShouldDelegateCallToRepository() {
        final long testEmployeeId = mockedTestEmployee.getId();
        service.getById(testEmployeeId);
        verify(repository).findById(testEmployeeId);
    }

    @Test
    public void testSaveShouldDelegateCallToRepository() {
        final Employee testEmployee = mockedTestEmployee;
        service.save(testEmployee);
        verify(repository).save(testEmployee);
    }

    @Test
    public void testDeleteShouldDelegateCallToRepository() {
        final long testEmployeeId = mockedTestEmployee.getId();
        service.delete(testEmployeeId);
        verify(repository).deleteById(testEmployeeId);
    }

    @Test
    public void testUpdateShouldDelegateCallToRepository() {
        final Employee testEmployee = mockedTestEmployee;
        service.update(testEmployee);
        verify(repository).save(testEmployee);
    }
}