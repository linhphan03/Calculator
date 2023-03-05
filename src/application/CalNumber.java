package application;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

public class CalNumber {
	public StringProperty number = new SimpleStringProperty(this, "");
	
	public CalNumber() {
		this("0");
	}
	
	public CalNumber(String num) {
		setNumber(num);
	}
	
	public String getNumber() {
		return number.get();
	}
	
	public StringProperty numberProperty() {
		return number;
	}
	
	public DoubleProperty numberDoubleProperty() {
		return new SimpleDoubleProperty(Double.valueOf(getNumber()));
	}
	
	public double toDouble() {
		return Double.valueOf(getNumber());
	}
	
	public void setNumber(String newNumber) {
//		this.number.addListener((obs_p, old_p, new_p) -> {});
		this.number.set(newNumber);
	}
	
	public void addDigit(NumberButton newNumButton) {
		String myNum = getNumber();
		
		//0.1
		if (myNum.length() >= 1 && myNum.charAt(0) == '0') {	
			if (myNum.length() == 1) {
				myNum = "";
			}
			else if (getNumber().length() == 2 && getNumber().charAt(1) != '.') {
				myNum = myNum.substring(1);
			}
		}
		setNumber(myNum + newNumButton.toString());
	}
	
	public void addDot(DotButton dot) {
		if (!dot.isClicked()) {
			setNumber(getNumber() + dot.toString());
			dot.setClicked(true);
		}
	}
	
	public void add(CalNumber added) {
		double result = Double.valueOf(getNumber()) + Double.valueOf(added.getNumber());
		setNumber(String.valueOf(result));
	}
	
	public void subtract(CalNumber subtracted) {
		double result = Double.valueOf(getNumber()) - Double.valueOf(subtracted.getNumber());
		setNumber(String.valueOf(result));
	}
	
	public void multiply(CalNumber multiplied) {
		double result = Double.valueOf(getNumber()) * Double.valueOf(multiplied.getNumber());
		setNumber(String.valueOf(result));
	}
	
	public void divide(CalNumber divided) {
		double result = Double.valueOf(getNumber()) / Double.valueOf(divided.getNumber());
		System.out.println(result);
		setNumber(String.valueOf(result));
	}
	
	public void mod(CalNumber mod) {
		double result = Double.valueOf(getNumber()) % Double.valueOf(mod.getNumber());
		setNumber(String.valueOf(result));
	}
	
	public String toString() {
		return getNumber();
	}
	
	public static void function(CalNumber firstNum, CalNumber secondNum, TextField result) {
		for (NumberButton button : Calculator.digits){
			button.setOnAction(e -> {
				if (Calculator.currentSign == null) {
					firstNum.addDigit(button);
					result.textProperty().bind(Bindings.convert(firstNum.numberProperty()));
				}
				else {
					secondNum.addDigit(button);
					result.textProperty().bind(Bindings.convert(secondNum.numberProperty()));
				}
			});
		}
	}
}
