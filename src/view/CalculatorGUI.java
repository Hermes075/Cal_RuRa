package view;


import java.util.List;
import java.text.DecimalFormat;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.control.Label;


public class CalculatorGUI extends Scene implements CalculatorGUIInterface {

	private Button[] buttons; // Contains all the buttons
	private Button closeButton; // Close button
	private Stage dialogStage;
	
	// Text on top of the calculator
	private Label ecranLabel; // The result of the operation
	private Label ecranHistorique; // Operands history stack
	
	public CalculatorGUI() {
		// Constructor of the parent class (Scene) with an instance of BorderPane and the dimensions   
		super(new BorderPane(), 400, 500); 
		// Layout creation of the calculator
		BorderPane root = createCalculatorLayout();
		// "CLOSE" button to reset
	    closeButton = new Button("CLOSE");
		// New dialog window
    	dialogStage = new Stage();
		// Associate the layout to the root of the scene
		((BorderPane) getRoot()).setCenter(root);
		// Load the CSS style sheet
        String cssPath = getClass().getResource("application.css").toExternalForm();
        getStylesheets().add(cssPath);
	}

	private BorderPane createCalculatorLayout() {
	    BorderPane borderPane = new BorderPane();
	    ecranLabel = new Label(""); // Create a label for the result
	    ecranLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
	    ecranLabel.setPrefHeight(200);
	    borderPane.setTop(ecranLabel);

	    GridPane gridPane = new GridPane();
	    borderPane.setCenter(gridPane);

		// Creation of the buttons and addition of the event handlers
	    buttons = createAndSetupButtons();
	    gridPane.addColumn(0, buttons[19], buttons[7], buttons[4], buttons[1], buttons[0]);
        gridPane.addColumn(1, buttons[13], buttons[8], buttons[5], buttons[2], buttons[11]);
        gridPane.addColumn(2, buttons[12], buttons[9], buttons[6], buttons[3], buttons[10]);
        gridPane.addColumn(3, buttons[17], buttons[16], buttons[15], buttons[14], buttons[18]);

		// Create the "ENTER" button to fill the last line
        buttons[20].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    gridPane.add(buttons[20], 0, 5); // Add the button to the gridPane

		// Add the history screen to the right of the screen
	    VBox historiqueBox = new VBox(); // Create a VBox to contain the history
	    historiqueBox.setAlignment(Pos.TOP_CENTER); // Center the VBox vertically
	    Label historiqueLabel = new Label("Operands Stack"); // Create a Label for the history
	    historiqueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
	    historiqueLabel.setTextAlignment(TextAlignment.CENTER);

	    ecranHistorique = new Label(""); // Create a Label for the history
	    ecranHistorique.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	    ecranHistorique.setTextAlignment(TextAlignment.RIGHT);

	    historiqueBox.getChildren().addAll(historiqueLabel, ecranHistorique);
	    historiqueBox.setSpacing(10);

	    borderPane.setRight(historiqueBox);

		// Allows the "ENTER" button to fill the last line
	    GridPane.setColumnSpan(buttons[20], 4); // Column 0 to 4 inclusive
	    GridPane.setHalignment(buttons[20], HPos.CENTER); // Centers the button horizontally
	    return borderPane;
	}

	// Buttons creation
	private Button[] createAndSetupButtons() {
		// Create an array of buttons of size 21
	    Button[] buttons = new Button[21];
	    String[] buttonLabels = {
	        "0", "1", "2", "3", "4",
	        "5", "6", "7", "8", "9",
	        "POP", ".", "SWAP", "+/-", "+",
	        "-", "x", "/", "<--", "AC","ENTER"
	    };
	    for (int i = 0; i < 21; i++) {
	        buttons[i] = new Button(buttonLabels[i]);
	        buttons[i].setPrefSize(100, 100);
	    }


	    return buttons;
	}

	// Update the label
	 public void affiche(String string) {
	        ecranLabel.setText(string);
	    }
	 
	 public void afficheResOp(String string) {
		 double result = Double.parseDouble(string);

		 // Get the formatted String and display
		 String formattedResult = formatDouble(result).toString();
		 ecranLabel.setText(formattedResult);
	 	}
	 
	 // To avoid the problem at the time of conversion
	 private String formatDouble(double value) {
		 // Handles the number of decimals dynamically
		 String pattern = "#." + new String(new char[10]).replace('\0', '#'); // Up to 10 decimals

		 DecimalFormat decimalFormat = new DecimalFormat(pattern);
		 return decimalFormat.format(value);
		}

	// Allows to update the history after each key button press
    public void updateHistorique(List<Double> historique) {
        StringBuilder historiqueText = new StringBuilder();
        for (Double valeur : historique) {
   		 	String formattedResult = formatDouble(valeur).toString();
            historiqueText.append(formattedResult);
            historiqueText.append("\n");
        }

        ecranHistorique.setText(historiqueText.toString());
    }
    
	// Error windows
    public void messageErreur(String message) {
		// Creation of a new dialog window
    	dialogStage = new Stage();
    	closeButton.setMaxWidth(Double.MAX_VALUE); // The maximum width
	    closeButton.setPrefHeight(50); // Height
    	dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Error Warning");
    	dialogStage.initOwner(ecranLabel.getScene().getWindow());
       
		// Create a TextFlow to display the text in a structured way
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefSize(400, 130); 
        textFlow.setTextAlignment(TextAlignment.CENTER);

		// Divide the message into lines
        String[] lines = message.split("\n"); // Line break as separator

		// Create a Text for each line of the message
        for (String line : lines) {
            Text text = new Text(line);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
            textFlow.getChildren().add(text);

			// Add a line break after each line of the message
            textFlow.getChildren().add(new Text("\n"));
        }
        
		// VBox parameters
        VBox vbox = new VBox(textFlow, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(100);

        Scene dialogScene = new Scene(vbox); 
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
    
	// Getters
    public Button[] getButtons() {
    	return buttons;
    }
    public Button getResetButton() {
    	return closeButton;
    }
    public Stage getDialogStage(){
    	return dialogStage;
    }

	@Override
	public void fermeDialogueStage() {
		dialogStage.close();
	}
}