package edu.northeastern.cs5200.model;

public class YoutubeWidget extends Widget {

	private String url;
	private Boolean shareable;
	private Boolean expandable;

	public YoutubeWidget(Widget w, String url, Boolean shareable, Boolean expandable) {
		super(w.getPage(), w.getName(), w.getWidth(), w.getHeight(), w.getCssClass(), w.getCssStyle(), w.getText(), w.getOrder());
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
		this.setType("youtube");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getShareable() {
		return shareable;
	}

	public void setShareable(Boolean shareable) {
		this.shareable = shareable;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	
}
