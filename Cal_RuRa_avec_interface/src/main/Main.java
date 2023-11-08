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
    	//Association avec le controller 
    	CalculatorGUI gui = new CalculatorGUI();
    	CalculatorControler controller = new CalculatorControler(gui); // on crée le controlleur pour la premiere fois
        primaryStage.setScene(gui);
        primaryStage.setTitle("Calculatrice");
        primaryStage.show();
    }

}
