package edu.northeastern.cs5200.model;


public class HtmlWidget extends Widget {

	private String html;
	
	public HtmlWidget(Widget w, String html) {
		super(w.getPage(), w.getName(), w.getWidth(), w.getHeight(), w.getCssClass(), w.getCssStyle(), w.getText(), w.getOrder());
		this.setHtml(html);
		this.setType("html");
	}
	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

}
