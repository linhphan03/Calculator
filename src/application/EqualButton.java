package application;


public class EqualButton extends SignButton {
	public EqualButton() {
		super("=");
		this.setStyle("-fx-background-color: #f1a23b; -fx-border-color: #424244; -fx-border-width: 1px; -fx-font-weight: bold; -fx-text-fill: white");
	}

	@Override
	public String act(CalNumber firstNumber, CalNumber secondNumber) {
		// Do nothing
		return null;
	}
	
	public String act(CalNumber firstNumber, CalNumber secondNumber, SignButton sign) {
		String result;
		if (secondNumber.toString() == "") {
			result = firstNumber.toString();
		}
		else {
			result = sign.act(firstNumber, secondNumber);
		}
		return result;
	}
}
