package edu.northeastern.cs5200.model;

import java.util.Date;

public class Person {

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Date dob;
	
	
	public Person(String firstName2, String lastName2, String username2, String password2, String email2, Date dob2) {
		this.setFirstName(firstName2);
		this.setLastName(lastName2);
		this.setUsername(username2);
		this.setPassword(password2);
		this.setEmail(email2);
		this.setDob(dob2);
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	
}
