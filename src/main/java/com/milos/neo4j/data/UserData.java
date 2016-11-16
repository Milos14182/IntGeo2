package com.milos.neo4j.data;

import org.springframework.web.multipart.MultipartFile;


//@JsonIgnoreProperties("meta")
public class UserData {
	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String age;
	private String gender = "Male";
	private String email;
	private CityData city;
	private MultipartFile userImage;
	private String base64Image;
	private Long gameScore;
	private Boolean admin;

	public Long getGameScore() {
		return gameScore;
	}

	public void setGameScore(Long gameScore) {
		this.gameScore = gameScore;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public MultipartFile getUserImage() {
		return userImage;
	}

	public void setUserImage(MultipartFile userImage) {
		this.userImage = userImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CityData getCity() {
		return city;
	}

	public void setCity(CityData city) {
		this.city = city;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
}
