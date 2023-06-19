package com.olmez.coremicro.services;

import java.util.List;

import com.olmez.coremicro.model.Employee;

public interface EmployeeService {

    // Create
    Long addEmployee(Employee emp);

    // Read-I
    List<Employee> getAllEmployees();

    // Read-II
    Employee getEmployeeById(long empId);

    // Update-I
    Long updateEmployee(Employee givenEmp);

    // Update-II
    Long updateEmployee(Long existingEmpId, Employee givenEmp);

    // Delete
    boolean deleteEmployeeById(Long empId);

}
