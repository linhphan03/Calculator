package application;


public class DivideButton extends SignButton {
	public DivideButton() {
		super("/");
	}
	
	public String act(CalNumber firstNumber, CalNumber secondNumber) {
		if (secondNumber.getNumber().equals("0")) {
			return "Infinity";
		}
		firstNumber.divide(secondNumber);
		secondNumber.setNumber("");
		return firstNumber.toString();
	}
}
