package com.example.employeemanagement.repository;

import com.example.employeemanagement.Employee;
import com.example.employeemanagement.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest  // Spins up an in-memory database & configures repository beans
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveAndFindEmployee() {
        // Create employee
        Employee employee = new Employee("Alice", "HR", 50000.0);

        // Save employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Retrieve employee by ID
        Optional<Employee> retrievedEmployee = employeeRepository.findById(savedEmployee.getId());

        // Assert employee is found and matches
        assertThat(retrievedEmployee).isPresent();
        assertThat(retrievedEmployee.get().getName()).isEqualTo("Alice");
        assertThat(retrievedEmployee.get().getDepartment()).isEqualTo("HR");
        assertThat(retrievedEmployee.get().getSalary()).isEqualTo(50000.0);
    }
}
