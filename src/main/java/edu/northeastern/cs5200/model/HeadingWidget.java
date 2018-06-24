package edu.northeastern.cs5200.model;

public class HeadingWidget extends Widget {

	private int size;
	
	public HeadingWidget(Widget w, int size) {
		super(w.getPage(), w.getName(), w.getWidth(), w.getHeight(), w.getCssClass(), w.getCssStyle(), w.getText(), w.getOrder());
		this.setSize(size);
		this.setType("heading");
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
}


