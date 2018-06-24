package edu.northeastern.cs5200.model;

import java.util.Collection;
import java.util.Date;

public class Website {
	
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private int visits;
	private Collection<Page> pages;
	private int id;
	
	public Website(String name, String description, Date created, Date updated, int visits) {
		
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;

	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public Collection<Page> getPages() {
		return pages;
	}

	public void setPages(Collection<Page> pages) {
		this.pages = pages;
	}
	
	
}
