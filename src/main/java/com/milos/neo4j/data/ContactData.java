package com.milos.neo4j.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("meta")
public class ContactData {
	private String firstname;
	private String lastname;
	private String email;
	private String message;
	private String gender = "Male";
	private Integer Age;

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getMessage() {
		return this.message;
	}

	public Integer getAge() {
		return this.Age;
	}

	public void setAge(final Integer age) {
		this.Age = age;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.Age.toString();
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}
}
