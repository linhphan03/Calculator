package application;

public class DotButton extends MyButton {
	boolean isClicked;
	
	public DotButton(String name) {
		super(name);
		this.setStyle("-fx-background-color: #86857b; -fx-text-fill: white; -fx-border-color: #424244; -fx-border-width: 1px");
	}

	public boolean isClicked() {
		return isClicked;
	}

	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
}
