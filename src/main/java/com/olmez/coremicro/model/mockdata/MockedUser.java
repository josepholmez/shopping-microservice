package com.olmez.coremicro.model.mockdata;

import com.olmez.coremicro.model.User;
import com.olmez.coremicro.model.enums.UserType;

public class MockedUser extends User {
	private static long generatedId = 1L;

	public MockedUser(String firstName, String lastName) {
		super(firstName + lastName, firstName, lastName, firstName + "@mocked.com", UserType.REGULAR);
		setId(generatedId++);
	}

}
