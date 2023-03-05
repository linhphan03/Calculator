package application;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TextField;

public class ClearButton extends MyButton {

	public ClearButton() {
		super("C");
		this.setStyle("-fx-background-color: #555556; -fx-text-fill: white; -fx-border-color: #424244; -fx-border-width: 1px; -fx-font-weight: bold");
	}
	
	public void function(CalNumber firstNum, CalNumber secondNum, TextField result, DotButton dot) {
		firstNum.setNumber("0");
		secondNum.setNumber("");
		result.textProperty().bind(Bindings.convert(firstNum.numberDoubleProperty()));
		Calculator.currentSign = null;
		dot.setClicked(false);
		for (NumberButton button : Calculator.digits) {
			NumberButton.addDigitChoice(firstNum, secondNum, result, button);
		}
	}
}
