package controler;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import javafx.scene.control.Button;
import model.CalculatorModel;
import view.CalculatorGUI;


public class CalculatorControler {
	// on c
	private CalculatorGUI interfaceCalculatrice; // Interface graphique de la calculatrice
	private CalculatorModel calculatrice; // La calculatrice fera les calculs
		
	public CalculatorControler (CalculatorGUI interfaceCalculatrice) {
		calculatrice = new CalculatorModel(); // on crée une calculatrice quand on lance pour la premiere fois
		this.interfaceCalculatrice = interfaceCalculatrice;
		Button[] buttons = this.interfaceCalculatrice.getButtons();
		
		// Gestionnaire d'événements pour chaque bouton
        for (int i = 0; i < buttons.length; i++) {
            final int buttonIndex = i;
            buttons[i].setOnAction(e -> handleButtonClick(buttonIndex));
        }

        this.interfaceCalculatrice.getResetButton().setOnAction(e -> {
            // Réinitialiser la fenêtre principale et la mémoire de la calculatrice
            handleButtonClick(19);
            // Fermer la fenêtre de dialogue
            this.interfaceCalculatrice.getDialogStage().close();
        });

	}
	
	
	
	public void handleButtonClick(int buttonIndex) {
		
	    // Liste des index correspondants aux chiffres sur l'interface
	    List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	    for (int index : indexChiffres) {
	        if (index == buttonIndex) {
	        	//Vérification du mode de la calculatrice
	            calculatrice.ajoutEnCours(String.valueOf(index));
	            interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	            //interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	        }
	    }

	    // Si l'index est dans la plage des opérations
	    if (buttonIndex >= 14 && buttonIndex <= 17) {
	        effectuerOperationSelonIndex(buttonIndex);
	        //interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    // Pour le bouton RR
	    if (buttonIndex == 18) {
	        handleRROrAC(false);// Ne pas effacer la pile
	        //interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }

	    // Pour le bouton AC
	    if (buttonIndex == 19) {
	        handleRROrAC(true);// Effacer la pile
	        //interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    //Pour le bouton +/-
	    if (buttonIndex == 13) {
	    	calculatrice.inverseEnCours();
	    	interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
		    String historique = calculatrice.getOperandeAsString();
	        interfaceCalculatrice.updateHistory(historique);
	    	//interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    //Bouton .
	    if (buttonIndex == 11) {
	    	calculatrice.ajoutEnCours(".");
	    	interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	    	//interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    if (buttonIndex == 10) {
	    	calculatrice.delete();
	    	interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    	String historique = calculatrice.getOperandeAsString();
	        interfaceCalculatrice.updateHistory(historique);
	    }
	    
	    //Bouton SWAP
	    if (buttonIndex == 12) {
	    	calculatrice.swap();
	    	interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
		    String historique = calculatrice.getOperandeAsString();
	        interfaceCalculatrice.updateHistory(historique);
	    } 
	}

	//Permet de ne pas répéter les mêmes lignes de code pour chaque opération
	private void effectuerOperationSelonIndex(int buttonIndex) {
	    try {
		switch (buttonIndex) {
	        case 14:
	            calculatrice.addition();
	            break;
	        case 15:
	            calculatrice.soustraction();
	            break;
	        case 16:
	            calculatrice.multiplication();
	            break;
	        case 17:
	            calculatrice.division();
	            break;
	    }
	    calculatrice.clearEnCours();
	    interfaceCalculatrice.updateLabel(calculatrice.getResultat());
	    String historique = calculatrice.getOperandeAsString();
        interfaceCalculatrice.updateHistory(historique);
	    //interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }catch (EmptyStackException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    } catch (IllegalArgumentException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    }
	    catch (ArithmeticException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    }
	    
	}
	
	private void handleRROrAC(boolean clearPile) {
	    calculatrice.ajoutPile(Double.parseDouble(calculatrice.getEnCours()));
	    calculatrice.clearEnCours();
	    interfaceCalculatrice.updateLabel(calculatrice.getEnCours());

	    if (clearPile) {
	        calculatrice.clearPile();
	    }
	    String historique = calculatrice.getOperandeAsString();
        interfaceCalculatrice.updateHistory(historique);
	}
}