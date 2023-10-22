package view;

import controler.CalculatorControler;
import javafx.application.Application;
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

public class CalculatorGUI extends Scene{

	private Button[] buttons;
	private Button resetButton;
	private Stage dialogStage;
	
    // Texte en haut de la caulatrice
	private Label ecranLabel;
	
	public CalculatorGUI() {
		super(new BorderPane(), 400, 400); // Appel au constructeur de la classe mère (Scene) avec une instance de BorderPane et les dimensions    
		// Crée le layout de la calculatrice
		BorderPane root = createCalculatorLayout();
		// Créer un bouton "AC" pour réinitialiser
	    resetButton = new Button("AC");
	    // Créer une nouvelle fenêtre de dialogue
    	dialogStage = new Stage();
		// Associe le layout à la racine de la scène
		((BorderPane) getRoot()).setCenter(root);
	}

    private BorderPane createCalculatorLayout() {
        BorderPane borderPane = new BorderPane();
        ecranLabel = new Label("0.0"); // Créer un Label
        ecranLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        ecranLabel.setPrefHeight(200);
        borderPane.setTop(ecranLabel); 

        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);

        // Création des boutons et ajout des gestionnaires d'événements
        buttons = createAndSetupButtons();
        gridPane.addColumn(0, buttons[19], buttons[7], buttons[4], buttons[1], buttons[0]);
        gridPane.addColumn(1, buttons[13], buttons[8], buttons[5], buttons[2], buttons[11]);
        gridPane.addColumn(2, buttons[12], buttons[9], buttons[6], buttons[3], buttons[10]);
        gridPane.addColumn(3, buttons[17], buttons[16], buttons[15], buttons[14], buttons[18]);

        return borderPane;
    }
    // création des buttons
    private Button[] createAndSetupButtons() {
        Button[] buttons = new Button[20];
        String[] buttonLabels = {
            "0", "1", "2", "3", "4",
            "5", "6", "7", "8","9",
            "=", ",", "%", "+/-","+",
            "-", "x", "/", "RR","AC"
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setPrefSize(100, 100);
        }

        return buttons;
    }
    
    // mettre à jour le label
    public void updateLabel(Double value) {
        ecranLabel.setText(value.toString());
    }
    
    
    
    public void messageErreur(String message) {
	    // Créer une nouvelle fenêtre de dialogue
    	dialogStage = new Stage();
    	resetButton.setMaxWidth(Double.MAX_VALUE); // Définir la largeur maximale
	    resetButton.setPrefHeight(50); // Définir la hauteur
    	dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Attention Erreur");
    	dialogStage.initOwner(ecranLabel.getScene().getWindow());
        // Créer un TextFlow pour afficher le texte
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefSize(400, 130); 
        textFlow.setTextAlignment(TextAlignment.CENTER); // Centrer le texte

        // Séparer le message en lignes
        String[] lines = message.split("\n");

        // Créer un Text pour chaque ligne du message
        for (String line : lines) {
            Text text = new Text(line);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK); // Couleur du texte
            textFlow.getChildren().add(text);

            // Ajouter un saut de ligne après chaque ligne du message
            textFlow.getChildren().add(new Text("\n"));
        }

        VBox vbox = new VBox(textFlow, resetButton);
        vbox.setAlignment(Pos.CENTER); // Centrer le contenu dans la VBox
        vbox.setSpacing(100); // Espacement entre les éléments

        Scene dialogScene = new Scene(vbox); 
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
    public Button[] getButtons() {
    	return buttons;
    }
    public Button getResetButton() {
    	return resetButton;
    }
    public Stage getDialogStage(){
    	return dialogStage;
    }
}


