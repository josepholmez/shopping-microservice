package com.olmez.coremicro.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.olmez.coremicro.model.Employee;
import com.olmez.coremicro.model.mockdata.MockEmployee;
import com.olmez.coremicro.repositories.EmployeeRepository;
import com.olmez.coremicro.services.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl service;
    @Mock
    private EmployeeRepository empRepository;

    private Employee emp;
    private Employee emp2;

    @BeforeEach
    public void setup() {
        emp = new MockEmployee("Employee");
        emp2 = new MockEmployee("Employee2");
    }

    @Test
    void testGetEmployees() {
        // arrange
        when(empRepository.findAll()).thenReturn(List.of(emp, emp2));
        // act
        var employees = service.getAllEmployees();
        // assert
        assertThat(employees).hasSize(2);
        assertThat(employees.get(0)).isEqualTo(emp);
        assertThat(employees.get(1)).isEqualTo(emp2);
    }

    @Test
    void testUpdateEmployee() {
        // arrange
        when(empRepository.getById(emp.getId())).thenReturn(emp);
        when(empRepository.save(any(Employee.class))).thenReturn(emp);
        var newEmp = new MockEmployee("New Employee");
        // act
        var updated = service.updateEmployee(emp.getId(), newEmp);
        // assert
        assertThat(updated).isEqualTo(emp.getId());
    }

}
