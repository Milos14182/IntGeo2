package com.milos.neo4j.domain.nodes;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NodeEntity
public class User {
	private @GraphId Long id;

	@NotNull(message = "Firstname input is empty.")
	@Property(name = "firstname")
	private String firstname;

	@NotNull(message = "Lastname input is empty.")
	@Property(name = "lastname")
	private String lastname;

	@NotNull(message = "Username input is empty.")
	@Property(name = "username")
	private String username;

	@NotNull(message = "Please enter password.")
	@Size(min = 6, max = 15, message = "Your password must be between 6 and 15 characters.")
	@Property(name = "password")
	private String password;

	@NotNull(message = "Age is not set")
	@NumberFormat
	@Property(name = "age")
	private String age;

	@NotNull
	@Property(name = "gender")
	private String gender = "Male";

	@NotNull(message = "Email can't be empty.")
	@Property(name = "email")
	private String email;
	
	@Property(name = "userImage")
	private byte[] userImage;
	
	@Property(name = "gameScore")
	private Long gameScore;
	
	@JsonIgnore
	@Property(name="admin")
	private Boolean admin;

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	@Relationship(type = "LIVES_IN_CITY", direction = Relationship.OUTGOING)
	private City city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(final String age) {
		this.age = age;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Long getGameScore() {
		return gameScore;
	}

	public void setGameScore(Long gameScore) {
		this.gameScore = gameScore;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
