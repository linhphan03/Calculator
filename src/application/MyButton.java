package application;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class MyButton extends Button {
	public MyButton(String name) {
		super(name);
	}
	
	public void setPosition(GridPane grid, int col, int row) {
		GridPane.setConstraints(this, col, row);
		grid.getChildren().add(this);
	}
	
	public void setSides(GridPane grid) {
		this.prefWidthProperty().bind(grid.widthProperty().divide(4));
		this.prefHeightProperty().bind(grid.heightProperty().divide(3));
	}
	
	public void setShape() {
		Rectangle buttonShape = new Rectangle(15,15);
		this.setShape(buttonShape);
	}
	
	public void init(GridPane grid, int col, int row) {
		setPosition(grid, col, row);
		setSides(grid);
		setShape();
	}
	
	public String toString() {
		return this.getText();
	}
}
