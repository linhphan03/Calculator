package application;

public class PlusButton extends SignButton{

	public PlusButton() {
		super("+");
	}

	public String act(CalNumber firstNumber, CalNumber secondNumber) {
		firstNumber.add(secondNumber);
		secondNumber.setNumber("");
		return firstNumber.toString();
	}
}
