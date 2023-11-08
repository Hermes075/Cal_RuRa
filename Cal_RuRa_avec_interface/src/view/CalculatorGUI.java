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

	private Button[] buttons; // contient tout les boutons
	private Button closeButton; // le close bouton 
	private Stage dialogStage;
	
	
    // Texte en haut de la calculatrice
	private Label ecranLabel; // le résultat 
	private Label ecranHistorique; // historique des opérandes
	
	public CalculatorGUI() {
		// Appel au constructeur de la classe mère (Scene) avec une instance de BorderPane et les dimensions    
		super(new BorderPane(), 400, 500); 
		// Crée le layout de la calculatrice
		BorderPane root = createCalculatorLayout();
		// Créer un bouton "CLOSE" pour réinitialiser
	    closeButton = new Button("CLOSE");
	    
	    // Créer une nouvelle fenêtre de dialogue
    	dialogStage = new Stage();
		// Associe le layout à la racine de la scène
		((BorderPane) getRoot()).setCenter(root);
        
     // Charger la feuille de style CSS
        String cssPath = getClass().getResource("application.css").toExternalForm();
        getStylesheets().add(cssPath);
	}

	private BorderPane createCalculatorLayout() {
	    BorderPane borderPane = new BorderPane();
	    ecranLabel = new Label(""); // Créer un Label
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

	    // Crée le bouton "ENTER" pour remplir la dernière ligne
        buttons[20].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    gridPane.add(buttons[20], 0, 5); // Ajoute le bouton à la dernière ligne

	    // Ajout de l'écran Historique à droite de l'écran
	    VBox historiqueBox = new VBox(); // Crée un VBox pour contenir l'historique
	    historiqueBox.setAlignment(Pos.TOP_CENTER); // Centre le contenu verticalement
	    Label historiqueLabel = new Label("Pile Opérandes"); // Crée un Label pour le titre
	    historiqueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
	    historiqueLabel.setTextAlignment(TextAlignment.CENTER);

	    ecranHistorique = new Label(""); // Crée un Label pour l'historique
	    ecranHistorique.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	    ecranHistorique.setTextAlignment(TextAlignment.CENTER);

	    historiqueBox.getChildren().addAll(historiqueLabel, ecranHistorique);
	    historiqueBox.setSpacing(10);

	    borderPane.setRight(historiqueBox);

	    // Permet au bouton "ENTER" de remplir la dernière ligne
	    GridPane.setColumnSpan(buttons[20], 4); // Colonne 0 jusqu'à 4 inclus
	    GridPane.setHalignment(buttons[20], HPos.CENTER); // Centre le bouton horizontalement

	    return borderPane;
	}

	// Création des buttons
	private Button[] createAndSetupButtons() {
	    // Crée un tableau de boutons de taille 21
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

    
    // Met à jour le label
	 public void affiche(String string) {
	        ecranLabel.setText(string);
	    }
	 
	 public void afficheResOp(String string) {
		 double result = Double.parseDouble(string);

		 // Récupère la String formatée et affiche
		 String formattedResult = formatDouble(result).toString();
		 ecranLabel.setText(formattedResult);
	 	}
	 
	 // pour eviter le pb au moment de la conversion 
	 private String formatDouble(double value) {
		 // Gère le nb de décimales dynamiquement
		 String pattern = "#." + new String(new char[10]).replace('\0', '#'); // Format jusqu'à 10 décimales

		 DecimalFormat decimalFormat = new DecimalFormat(pattern);
		 return decimalFormat.format(value);
		}

    // Permet de mettre à jour l'historique après chaque appui de bouton clé
    public void updateHistorique(List<Double> historique) {
        StringBuilder historiqueText = new StringBuilder();
        for (Double valeur : historique) {
   		 	String formattedResult = formatDouble(valeur).toString();
            historiqueText.append(formattedResult);
            historiqueText.append("\n");
        }

        ecranHistorique.setText(historiqueText.toString());
    }
    

    
    
    // fenetre d'erreurs 
    public void messageErreur(String message) {
	    // Création d'une nouvelle fenêtre de dialogue
    	dialogStage = new Stage();
    	closeButton.setMaxWidth(Double.MAX_VALUE); // la largeur maximale
	    closeButton.setPrefHeight(50); // la hauteur
    	dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Attention Erreur");
    	dialogStage.initOwner(ecranLabel.getScene().getWindow());
       
    	// Créer un TextFlow pour afficher le texte de manière structuré
        TextFlow textFlow = new TextFlow();
        textFlow.setPrefSize(400, 130); 
        textFlow.setTextAlignment(TextAlignment.CENTER);

        // Séparer le message en lignes
        String[] lines = message.split("\n"); // saut de ligne comme séparateur

        // Créer un Text pour chaque ligne du message
        for (String line : lines) {
            Text text = new Text(line);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
            textFlow.getChildren().add(text);

            // Ajouter un saut de ligne après chaque ligne du message
            textFlow.getChildren().add(new Text("\n"));
        }
        
        //Paramètres de la VBox
        VBox vbox = new VBox(textFlow, closeButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(100);

        Scene dialogScene = new Scene(vbox); 
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }
    
    // des getteurs
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