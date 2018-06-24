package edu.northeastern.cs5200.model;


public class ImageWidget extends Widget {

	private String src;
	
	public ImageWidget(Widget w, String src) {
		super(w.getPage(), w.getName(), w.getWidth(), w.getHeight(), w.getCssClass(), w.getCssStyle(), w.getText(), w.getOrder());
		this.setSrc(src);
		this.setType("image");
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
