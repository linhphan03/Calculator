package application;

public class SubtractButton extends SignButton {
	public SubtractButton() {
		super("-");
	}

	public String act(CalNumber firstNumber, CalNumber secondNumber) {
		firstNumber.subtract(secondNumber);
		secondNumber.setNumber("");
		return firstNumber.toString();
	}
}
