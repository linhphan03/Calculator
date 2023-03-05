package application;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class SignButton extends MyButton {
	boolean isClicked;
	
	public SignButton(String name) {
		super(name);
		this.setStyle("-fx-background-color: #f1a23b; -fx-border-color: #424244; -fx-border-width: 1px; -fx-font-weight: bold; -fx-text-fill: white");
	}

	
	public static void initSign(GridPane buttonLayout, CalNumber firstNum, CalNumber secondNum, TextField result, DotButton dot) {
		PlusButton plus = new PlusButton();
		Calculator.signs[4] = plus;
		
		SubtractButton subtract = new SubtractButton();
		Calculator.signs[3] = subtract;
		
		MultiplyButton multiply = new MultiplyButton();
		Calculator.signs[2] = multiply;
		
		DivideButton divide = new DivideButton();
		Calculator.signs[1] = divide;
		
		ModButton mod = new ModButton();
		Calculator.signs[0] = mod;
		
		for (int i = 0; i < Calculator.signs.length; i++) {
			SignButton sign = Calculator.signs[i];
			sign.init(buttonLayout, 3, i);
			sign.setOnAction(e -> {
				dot.setClicked(false);
				sign.function(firstNum, secondNum, result, dot);
			});
		}
	}

	public boolean isClicked() {
		return isClicked;
	}


	public void setClicked(boolean isClicked) {
		this.isClicked = isClicked;
	}
	
	public void function(CalNumber firstNum, CalNumber secondNum, TextField result, DotButton dot) {
		/* If second null:
		 * 		second = ""
		 * 		curentSign = this
		 * 		result binds with second
		 * If second not "": sign acc as an equal sign	
		 */
		if (secondNum.toString().equals("")) {
			for (NumberButton button : Calculator.digits) {
				button.setOnAction(e1 -> {
					secondNum.addDigit(button);
					result.textProperty().bind(Bindings.convert(secondNum.numberDoubleProperty()));
				});
			}
		}
		else {		
			result.clear();
			String newResult = Calculator.currentSign.act(firstNum, secondNum);
			result.textProperty().bind(Bindings.convert(new SimpleStringProperty(newResult)));
		}
		Calculator.currentSign = this;	
	}
	
	public abstract String act(CalNumber firstNumber, CalNumber secondNumber);
}
