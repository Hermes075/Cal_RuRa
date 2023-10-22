package application;

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

public class Interface extends Application {
	
	
	// Créez une instance de SampleController
	private SampleController controller;
	
    // Texte en haut de la caulatrice
	private Label ecranLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	//association avec le controller 
    	controller = new SampleController(); // on crée le controlleur pour la premiere fois
    	controller.setInterface(this); // cette controller sera associée à cette interface
    	
        BorderPane root = createCalculatorLayout();

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculatrice");
        primaryStage.show();
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
        Button[] buttons = createAndSetupButtons();
        gridPane.addColumn(0, buttons[19], buttons[7], buttons[4], buttons[1], buttons[0]);
        gridPane.addColumn(1, buttons[13], buttons[8], buttons[5], buttons[2], buttons[11]);
        gridPane.addColumn(2, buttons[12], buttons[9], buttons[6], buttons[3], buttons[10]);
        gridPane.addColumn(3, buttons[17], buttons[16], buttons[15], buttons[14], buttons[18]);

        // Gestionnaire d'événements pour chaque bouton
        for (int i = 0; i < buttons.length; i++) {
            final int buttonIndex = i;
            buttons[i].setOnAction(e -> controller.handleButtonClick(buttonIndex));
        }

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
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ecranLabel.getScene().getWindow());
        dialogStage.setTitle("Attention Erreur");

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

        // Créer un bouton "AC" pour réinitialiser
        Button resetButton = new Button("AC");
        resetButton.setMaxWidth(Double.MAX_VALUE); // Définir la largeur maximale
        resetButton.setPrefHeight(50); // Définir la hauteur

        resetButton.setOnAction(e -> {
            // Réinitialiser la fenêtre principale et la mémoire de la calculatrice
            controller.handleButtonClick(19);
            // Fermer la fenêtre de dialogue
            dialogStage.close();
        });

        VBox vbox = new VBox(textFlow, resetButton);
        vbox.setAlignment(Pos.CENTER); // Centrer le contenu dans la VBox
        vbox.setSpacing(100); // Espacement entre les éléments

        Scene dialogScene = new Scene(vbox); 
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }




}


