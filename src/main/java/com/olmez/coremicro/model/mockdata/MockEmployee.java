package com.olmez.coremicro.model.mockdata;

import java.time.LocalDate;

import com.olmez.coremicro.model.Employee;

public class MockEmployee extends Employee {
    private static long generatedId = 1L;

    public MockEmployee(String name) {
        setId(generatedId++);
        setName(name);
        setEmail(name + "@email.com");
        var date = LocalDate.of(2000, 1, 1).plusDays(generatedId++);
        setDob(date);
    }

}
