package controler;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import javafx.scene.control.Button;
import model.CalculatorModel;
import view.CalculatorGUI;


public class CalculatorControler {
	private CalculatorGUI interfaceCalculatrice; // Graphical User Interface
	private CalculatorModel calculatrice; // La calculatrice fera les calculs
		
	public CalculatorControler (CalculatorGUI interfaceCalculatrice) {
		calculatrice = new CalculatorModel(); // On crée une calculatrice quand on lance pour la premiere fois
		this.interfaceCalculatrice = interfaceCalculatrice;
		Button[] buttons = this.interfaceCalculatrice.getButtons();
		
		// Gestionnaire d'événements pour chaque bouton
        for (int i = 0; i < buttons.length; i++) {
            final int buttonIndex = i;
            buttons[i].setOnAction(e -> handleButtonClick(buttonIndex));
        }
        

        this.interfaceCalculatrice.getResetButton().setOnAction(e -> {
        	// Fermer la fenêtre de dialogue
            this.interfaceCalculatrice.fermeDialogueStage();
        });

	}
	
	
	
	public void handleButtonClick(int buttonIndex) {
		
	    // Liste des index correspondants aux chiffres sur l'interface
	    List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	    for (int index : indexChiffres) {
	        if (index == buttonIndex) {
	        	//Vérification du mode de la calculatrice
	            calculatrice.ajoutEnCours(String.valueOf(index));
	            interfaceCalculatrice.affiche(calculatrice.getEnCours());
	            interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	        }
	    }

	    // Si l'index est dans la plage des opérations
	    if (buttonIndex >= 14 && buttonIndex <= 17 ) {
	        effectuerOperationSelonIndex(buttonIndex);
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	 // Bouton "<--"
	    if (buttonIndex == 18) {
	    calculatrice.supprimer();
	    interfaceCalculatrice.affiche(calculatrice.getEnCours());	    
	    }
	    
	    // Bouton "ENTER"
	    if (buttonIndex == 20) {
	    handleRROrAC(false);
	    }

	    // Pour le bouton AC
	    if (buttonIndex == 19) {
	        handleRROrAC(true);// Effacer la pile
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    // Bouton +/-
	    if (buttonIndex == 13) {
	    	calculatrice.inverseEnCours();
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	    	interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    	
	    
	    // Bouton .
	    if (buttonIndex == 11) {
	    	calculatrice.ajoutEnCours(".");
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	    	interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    // Bouton POP
	    if (buttonIndex == 10) {
	    	calculatrice.pop();
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());

	    }
	    
	    // Bouton SWAP
	    if (buttonIndex == 12) {
	    	try{calculatrice.swap();
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());

	    	}
	    	catch (IllegalArgumentException e) {
		        interfaceCalculatrice.messageErreur(e.getMessage());
	    	}
	    } 
	}

	//Permet de ne pas répéter les mêmes lignes de code pour chaque opération
	private void effectuerOperationSelonIndex(int buttonIndex) {
	    try {
		switch (buttonIndex) {
	        case 14:
	            calculatrice.add();
	            break;
	        case 15:
	            calculatrice.substract();
	            break;
	        case 16:
	            calculatrice.multiply();
	            break;
	        case 17:
	            calculatrice.divide();
	            break;
	    }
	    calculatrice.clearEnCours();
	    interfaceCalculatrice.afficheResOp(calculatrice.getResultat());
	    interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }catch (EmptyStackException e) { // gestion des erreurs
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    } catch (IllegalArgumentException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    }
	    catch (ArithmeticException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    }
	    
	}
	
	
	// on gère ici le boutton ENTER et AC puisqu'ils se ressemblent
	private void handleRROrAC(boolean clearPile) {
		String EnCours = calculatrice.getEnCours();
		if (EnCours.length()==1 && EnCours.contains(".")) {
			EnCours = "0.0";
		}
	    calculatrice.push(Double.parseDouble(EnCours));
	    calculatrice.clearEnCours();
	    interfaceCalculatrice.affiche(calculatrice.getEnCours());

	    if (clearPile) {
	        calculatrice.clear();
	    }
	    interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	}
}