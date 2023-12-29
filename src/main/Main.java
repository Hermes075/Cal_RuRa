package main;

import controler.CalculatorControler;
import javafx.application.Application;
import javafx.stage.Stage;
import view.CalculatorGUI;

public class Main extends Application{
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Association with the controller
    	CalculatorGUI gui = new CalculatorGUI();
    	CalculatorControler controller = new CalculatorControler(gui); // We create the controller for the first time
        primaryStage.setScene(gui);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }
}
