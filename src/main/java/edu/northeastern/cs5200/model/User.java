package edu.northeastern.cs5200.model;



public class User extends Person {
	
	private Boolean userAgreement;
	private String userKey;
	
	public User(Person p, Boolean userAgreement, String userKey) {
		super(p.getFirstName(), p.getLastName(), p.getUsername(), p.getPassword(), p.getEmail(), p.getDob());
		this.userAgreement = userAgreement;
		this.setUserKey(userKey);
	}
	
	public Boolean getUserAgreement() {
		return userAgreement;
	}

	public void setUserAgreement(Boolean userAgreement) {
		this.userAgreement = userAgreement;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	

}
