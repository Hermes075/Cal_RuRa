package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
    // Texte en haut de la caulatrice
    private TextArea displayText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = createCalculatorLayout();

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculatrice");
        primaryStage.show();
    }

    private BorderPane createCalculatorLayout() {
        BorderPane borderPane = new BorderPane();
        displayText = new TextArea("ce qui sera affiché");
        displayText.setPrefHeight(200);
        borderPane.setTop(displayText);

        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);

        // Création des boutons et ajout des gestionnaires d'événements
        Button[] buttons = createAndSetupButtons();
        gridPane.addColumn(0, buttons[4], buttons[3], buttons[2], buttons[1], buttons[0]);
        gridPane.addColumn(1, buttons[9], buttons[8], buttons[7], buttons[6], buttons[5]);
        gridPane.addColumn(2, buttons[14], buttons[13], buttons[12], buttons[11], buttons[10]);
        gridPane.addColumn(3, buttons[19], buttons[18], buttons[17], buttons[16], buttons[15]);

        // Gestionnaire d'événements pour chaque bouton
        for (int i = 0; i < buttons.length; i++) {
            final int buttonIndex = i;
            buttons[i].setOnAction(e -> handleButtonClick(buttonIndex));
        }

        return borderPane;
    }
    // création des buttons
    private Button[] createAndSetupButtons() {
        Button[] buttons = new Button[20];
        String[] buttonLabels = {
            "0", "1", "4", "7", "AC",
            ",", "2", "5", "8","+/-",
            "=", "3", "6", "9","%",
            "RR", "+", "-", "x","/"
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].setPrefSize(100, 100);
        }

        return buttons;
    }

    private void handleButtonClick(int buttonIndex) {

        //utilisation de boutton
        if (buttonIndex == 4) {
            // Exemple : Le bouton "AC" (effacer tout) a été cliqué
            displayText.setText("0");
        }
    }
}
