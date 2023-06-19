package com.olmez.coremicro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olmez.coremicro.model.Employee;
import com.olmez.coremicro.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "http://localhost:4200") // This allows to talk to port:5000 (ui-backend)
@AllArgsConstructor
public class EmployeeRestController {

    private final EmployeeService empService;

    // POST
    @PostMapping("/add")
    public Long addEmployee(@RequestBody Employee employee) {
        return empService.addEmployee(employee);
    }

    // GET
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return empService.getAllEmployees();
    }

    // PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateEmployee(@PathVariable("id") long id, @RequestBody Employee model) {
        long resId = empService.updateEmployee(id, model);
        return ResponseEntity.status(HttpStatus.OK).body(resId);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public boolean deleteEmployeeById(@PathVariable("id") long id) {
        return empService.deleteEmployeeById(id);
    }

    // Extra GET
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Employee resEmp = empService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resEmp);
    }

    // Extra PUT
    @PutMapping("/update")
    public Long updateEmployee(@RequestBody Employee model) {
        return empService.updateEmployee(model);
    }

}