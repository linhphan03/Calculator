package application;

public class MultiplyButton extends SignButton{
	public MultiplyButton() {
		super("x");
	}

	public String act(CalNumber firstNumber, CalNumber secondNumber) {
		firstNumber.multiply(secondNumber);
		secondNumber.setNumber("");
		return firstNumber.toString();
	}
}
