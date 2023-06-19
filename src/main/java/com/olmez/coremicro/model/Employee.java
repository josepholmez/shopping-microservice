package com.olmez.coremicro.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee extends BaseObject {

	private String name;
	private String email;
	private LocalDate dob;
	private boolean salaried;

	@OneToOne
	private Location address;

	public Employee(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email);
	}

}
