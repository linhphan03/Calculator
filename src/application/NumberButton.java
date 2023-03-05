package application;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class NumberButton extends MyButton {	
	private static int numOfClicks = 0;
	
	public NumberButton(String name) {
		super(name);
		this.setStyle("-fx-background-color: #86857b; -fx-text-fill: white; -fx-border-color: #424244; -fx-border-width: 1px; -fx-font-weight: bold");
	}
	
	public static void initNumber(GridPane grid) {
		int numButton = 1;
		for (int r = 1; r <= 3; r++) {
			for (int c = 0; c < 3; c++) {
				NumberButton button = new NumberButton(String.valueOf(numButton));
				button.init(grid, c, r);
				Calculator.digits[numButton++] = button;
			}
		}
	}
	//after clear is hit / another digit is clicked when previous calculation is done
	public static void addAnotherDigit(CalNumber firstNum, CalNumber secondNum, TextField result, DotButton dot) {		
		for (NumberButton button : Calculator.digits) {
			if (++numOfClicks == 1) {
				button.setOnAction(e1 -> {
					firstNum.setNumber(button.toString());
					secondNum.setNumber("");
					dot.setClicked(false);
					Calculator.currentSign = null;
					result.textProperty().bind(Bindings.convert(firstNum.numberDoubleProperty()));				
				});
			}
			else{
				addDigitChoice(firstNum, secondNum, result, button);
			}
		}
		numOfClicks = 0;
	}
	
	public static void addDigitChoice(CalNumber firstNum, CalNumber secondNum, TextField result, NumberButton button) {
		button.setOnAction(e -> {
			if (Calculator.currentSign == null) {
				firstNum.addDigit(button);
				result.textProperty().bind(Bindings.convert(firstNum.numberDoubleProperty()));
			}
			else {
				secondNum.addDigit(button);
				result.textProperty().bind(Bindings.convert(secondNum.numberDoubleProperty()));
			}
		});
	}
}