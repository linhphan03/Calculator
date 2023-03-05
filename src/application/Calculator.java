package application;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Calculator extends Application {
	CalNumber firstNum = new CalNumber();
	CalNumber secondNum = new CalNumber("");
	TextField result = new TextField();
	static SignButton currentSign;
	static CalNumber currentNum;
	static NumberButton[] digits = new NumberButton[10];
	static SignButton[] signs = new SignButton[5];
	
	public static void main(String[]args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Calculator");
		
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: #555556");
		layout.paddingProperty().bind(Bindings.createObjectBinding(() -> (new Insets(0, 0, 0, 0))));
		
		//set layout for textfield result
		setTextResult(layout);
		result.textProperty().bind(Bindings.convert(firstNum.numberDoubleProperty()));
		
		GridPane buttonLayout = new GridPane();
//		buttonLayout.setStyle("-fx-background-color: grey");
		buttonLayout.setPadding(new Insets(0, 0, 0, 0));
		buttonLayout.hgapProperty().bind(layout.widthProperty().multiply(0));
		buttonLayout.vgapProperty().bind(layout.heightProperty().multiply(0));
		layout.setCenter(buttonLayout);
		
		NumberButton zero = new NumberButton("0");
		digits[0] = zero;
		zero.init(buttonLayout, 1, 4);
		
		NumberButton.initNumber(buttonLayout);
		for (NumberButton button : digits){
			button.setOnAction(e -> {
				if (currentSign == null) {
					firstNum.addDigit(button);
					result.textProperty().bind(Bindings.convert(firstNum.numberDoubleProperty()));
				}
				else {
					secondNum.addDigit(button);
					result.textProperty().bind(Bindings.convert(secondNum.numberDoubleProperty()));

				}
			});
		}
		
		DotButton dot = new DotButton(".");
		dot.init(buttonLayout, 0, 4);
		dot.setOnAction(e -> {
			if (currentSign == null) {
				firstNum.addDot(dot);
			}
			else {
				secondNum.addDot(dot);
			}
		});
		
		EqualButton equal = new EqualButton();
		equal.init(buttonLayout, 2, 4);
		equal.setOnAction(e -> {
			firstNum.setNumber(equal.act(firstNum, secondNum, currentSign));
			String newResult = new String(firstNum.getNumber());
			
			if (newResult.equals("Infinity")) {
				result.textProperty().bind(new SimpleStringProperty(newResult));
			}
			else {
				if (newResult.length() > 30) {
					newResult = newResult.substring(0, 30);
				}
				result.textProperty().bind(new SimpleStringProperty(newResult));
			}
			dot.setClicked(false);
			
			NumberButton.addAnotherDigit(firstNum, secondNum, result, dot);
		});
		
		ClearButton clear = new ClearButton();
		clear.init(buttonLayout, 0, 0);
		clear.setOnAction(e -> {
			clear.function(firstNum, secondNum, result, dot);
			
		});
		
		SignButton.initSign(buttonLayout, firstNum, secondNum, result, dot);
		
		Scene scene = new Scene(layout, 300, 350);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		scene.getStylesheets().add(getClass().getResource("cssFormat.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private void setTextResult(BorderPane layout) {
		result.prefHeightProperty().bind(layout.heightProperty().divide(3.5));
		result.prefWidthProperty().bind(layout.widthProperty());
		result.setFont(Font.font("xenara", FontWeight.SEMI_BOLD, 20));
		result.setStyle("-fx-text-fill: white; -fx-background-color: #373434; -fx-display-caret: false;");
		result.setAlignment(Pos.BASELINE_RIGHT);
		layout.setTop(result);
	}
}
