package com.example.employeemanagement;

import com.example.employeemanagement.Employee;
import com.example.employeemanagement.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee("Bob", "IT", 60000.0);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.saveEmployee(employee);
        assertThat(saved).isEqualTo(employee);

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById() {
        Employee employee = new Employee("Bob", "IT", 60000.0);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.getEmployeeById(1L);
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Bob");

        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllEmployees() {
        Employee emp1 = new Employee("Alice", "HR", 50000.0);
        Employee emp2 = new Employee("Bob", "IT", 60000.0);
        when(employeeRepository.findAll()).thenReturn(List.of(emp1, emp2));

        List<Employee> all = employeeService.getAllEmployees();
        assertThat(all).hasSize(2);

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
