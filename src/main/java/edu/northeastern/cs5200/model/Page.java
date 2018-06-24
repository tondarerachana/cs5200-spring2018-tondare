package edu.northeastern.cs5200.model;

import java.util.Collection;
import java.util.Date;



public class Page {

	private String title;
	private String description;
	private Date created;
	private Date updated;
	private int views;
	private Website website;
	private int id;
	private Collection<Widget> widgets;
	
	
	public Page(Website website, String title, String description, Date created, Date updated, int views) {
		
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.views = views;
		this.website = website;
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public Website getWebsite() {
		return website;
	}
	public void setWebsite(Website website) {
		this.website = website;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(Collection<Widget> widgets) {
		this.widgets = widgets;
	}
	
	
	
}
