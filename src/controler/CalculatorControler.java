package controler;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;

import javafx.scene.control.Button;
import model.CalculatorModel;
import view.CalculatorGUI;


public class CalculatorControler {
	private CalculatorGUI interfaceCalculatrice; // Graphical User Interface
	private CalculatorModel calculatrice; // The calculator will do the calculations
		
	public CalculatorControler (CalculatorGUI interfaceCalculatrice) {
		calculatrice = new CalculatorModel(); // We create a calculator when we launch it for the first time
		this.interfaceCalculatrice = interfaceCalculatrice;
		Button[] buttons = this.interfaceCalculatrice.getButtons();
		
		// Event handler for each button
        for (int i = 0; i < buttons.length; i++) {
            final int buttonIndex = i;
            buttons[i].setOnAction(e -> handleButtonClick(buttonIndex));
        }
        

        this.interfaceCalculatrice.getResetButton().setOnAction(e -> {
        	// Close the dialog window
            this.interfaceCalculatrice.fermeDialogueStage();
        });

	}
	
	
	
	public void handleButtonClick(int buttonIndex) {
		
	    // Index list for the numbers on the interface
	    List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	    for (int index : indexChiffres) {
	        if (index == buttonIndex) {
				// Calculator mode verification
	            calculatrice.ajoutEnCours(String.valueOf(index));
	            interfaceCalculatrice.affiche(calculatrice.getEnCours());
	            interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	        }
	    }

		// If the index is in the range of operations
	    if (buttonIndex >= 14 && buttonIndex <= 17 ) {
	        effectuerOperationSelonIndex(buttonIndex);
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	 // Button "<--"
	    if (buttonIndex == 18) {
	    calculatrice.supprimer();
	    interfaceCalculatrice.affiche(calculatrice.getEnCours());	    
	    }
	    
	    // Button "ENTER"
	    if (buttonIndex == 20) {
	    handleRROrAC(false);
	    }

	    // Button "AC"
	    if (buttonIndex == 19) {
	        handleRROrAC(true);// Effacer la pile
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    // Button "+/-"
	    if (buttonIndex == 13) {
	    	calculatrice.inverseEnCours();
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	    	interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    	
	    // Button "."
	    if (buttonIndex == 11) {
	    	calculatrice.ajoutEnCours(".");
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	    	interfaceCalculatrice.updateHistorique(calculatrice.peek3());
	    }
	    
	    // Button "POP"
	    if (buttonIndex == 10) {
	    	calculatrice.pop();
	    	interfaceCalculatrice.affiche(calculatrice.getEnCours());
	        interfaceCalculatrice.updateHistorique(calculatrice.peek3());

	    }
	    
	    // Button "SWAP"
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

	// Allows not to repeat the same lines of code for each operation
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
	    }catch (EmptyStackException e) { // Error handling
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    } catch (IllegalArgumentException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    }
	    catch (ArithmeticException e) {
	        interfaceCalculatrice.messageErreur(e.getMessage());
	    }
	    
	}
	
	// Handle the ENTER and AC buttons since they are similar
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