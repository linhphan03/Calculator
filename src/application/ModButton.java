package application;

public class ModButton extends SignButton {

	public ModButton() {
		super("%");
		// TODO Auto-generated constructor stub
	}
	
	public String act(CalNumber firstNumber, CalNumber secondNumber) {
		firstNumber.mod(secondNumber);
		secondNumber.setNumber("");
		return firstNumber.toString();
	}
}
