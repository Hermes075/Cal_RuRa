package application;

import calculatricemodel.CalculatriceModel;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;


public class SampleController {
	// on c
	private Interface interfaceCalculatrice; // c'est l'interface graphique de la calculatrice
	private CalculatriceModel calculatrice; // c'est la calculatrice ( qui fera les calculs )
		
	// on fait ici une association avec l'interface
	public void setInterface(Interface app) {
        this.interfaceCalculatrice = app;
    }
	
	public SampleController () {
		calculatrice = new CalculatriceModel(); // on crée une calculatrice quand on lance pour la premiere fois
	}
	
	
	public void handleButtonClick(int buttonIndex) {
		
	    // Liste des index correspondants aux chiffres sur l'interface
	    List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	    for (int index : indexChiffres) {
	        if (index == buttonIndex) {
	        	//Vérification du mode de la calculatrice
	            calculatrice.ajoutEnCours(Double.valueOf(index));
	            interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	        }
	    }

	    // Si l'index est dans la plage des opérations
	    if (buttonIndex >= 14 && buttonIndex <= 17) {
	        effectuerOperationSelonIndex(buttonIndex);
	    }

	    if (buttonIndex == 18) {
	        handleRROrAC(false); // Ne pas effacer la pile
	    }

	    // Pour le bouton AC
	    if (buttonIndex == 19) {
	        handleRROrAC(true); // Effacer la pile
	    }
	    
	    //Pour le bouton +/-
	    if (buttonIndex == 13) {
	    	calculatrice.inverseEnCours();
	    	interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	    }
	    
	    //Bouton .
	    if (buttonIndex == 11) {
	    	calculatrice.inverserBoolean();
	    }
	    
	    //Bouton %
	    if (buttonIndex == 12) {
	    	calculatrice.enCoursPourcent();
	    	interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	    }
	    

	    System.out.println("Bouton pressé : " + buttonIndex);
	     
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
	    calculatrice.ajoutPile(calculatrice.getEnCours());
	    calculatrice.clearEnCours();
	    interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	    calculatrice.reinitBoolean();
	    calculatrice.reinitCompteur();
	    calculatrice.reinitBoolPositif();

	    if (clearPile) {
	        calculatrice.clearPile();
	    }
	}
}