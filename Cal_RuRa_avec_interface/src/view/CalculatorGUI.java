package view;

import java.util.List;

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

	private Button[] buttons;
	private Button resetButton;
	private Stage dialogStage;
	
    // Texte en haut de la caulatrice
	private Label ecranLabel;
	private Label historyLabel;
	private Label ecranHistorique;
	
	public CalculatorGUI() {
		// Appel au constructeur de la classe mère (Scene) avec une instance de BorderPane et les dimensions    
		super(new BorderPane(), 400, 400); 
		// Crée le layout de la calculatrice
		BorderPane root = createCalculatorLayout();
		// Créer un bouton "AC" pour réinitialiser
	    resetButton = new Button("AC");
	    // Créer une nouvelle fenêtre de dialogue
    	dialogStage = new Stage();
		// Associe le layout à la racine de la scène
		((BorderPane) getRoot()).setCenter(root);
        historyLabel = new Label("Historique : ");
        historyLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        historyLabel.setAlignment(Pos.CENTER);
        ((BorderPane) getRoot()).setTop(historyLabel);
	}

    private BorderPane createCalculatorLayout() {
        BorderPane borderPane = new BorderPane();
        ecranLabel = new Label("0.0"); // Créer un Label
        ecranLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        ecranLabel.setPrefHeight(200);
        borderPane.setTop(ecranLabel); 

     // Ajout de l'écran Historique à droite de l'écrans
        ecranHistorique = new Label("0.0"); // Créer un Label pour l'historique
        ecranHistorique.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ecranHistorique.setTextAlignment(TextAlignment.CENTER);
        borderPane.setRight(ecranHistorique);
        
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
            "=", ",", "SWAP", "+/-","+",
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
    
    public void updateHistorique(List<Double> historique) {
        StringBuilder historiqueText = new StringBuilder();

        for (Double valeur : historique) {
            historiqueText.append(valeur).append("\n");
        }

        ecranHistorique.setText(historiqueText.toString());
    }
    
    public void updateHistory(String history) {
        historyLabel.setText("Historique : " + history);
    }
    
    
    
    public void messageErreur(String message) {
	    // Créer une nouvelle fenêtre de dialogue
    	dialogStage = new Stage();
    	resetButton.setMaxWidth(Double.MAX_VALUE); // la largeur maximale
	    resetButton.setPrefHeight(50); // la hauteur
    	dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Attention Erreur");
    	dialogStage.initOwner(ecranLabel.getScene().getWindow());
       
    	// Créer un TextFlow pour afficher le texte de manière structurer
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefSize(400, 130); 
        textFlow.setTextAlignment(TextAlignment.CENTER); // Centrer le texte

        // Séparer le message en lignes
        String[] lines = message.split("\n"); // saut de ligne comme séparateur

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


