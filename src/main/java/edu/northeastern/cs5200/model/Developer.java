package edu.northeastern.cs5200.model;



public class Developer extends Person {

	private String developerKey;
	private int id;
	
	public Developer(Person p, String developerKey) {
		super(p.getFirstName(), p.getLastName(), p.getUsername(), p.getPassword(), p.getEmail(), p.getDob());
		this.setDeveloperKey(developerKey);
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
