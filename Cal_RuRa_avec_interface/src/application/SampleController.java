package application;

import calculatricemodel.CalculatriceModel;
import java.util.Arrays;
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
	            calculatrice.ajoutEnCours(Double.valueOf(index));
	            interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	        }
	    }

	    // Si l'index est dans la plage des opérations
	    if (buttonIndex >= 14 && buttonIndex <= 17) {
	        effectuerOperationSelonIndex(buttonIndex);
	    }

	    // Pour le bouton RR
	    if (buttonIndex == 18) {
	        calculatrice.ajoutPile(calculatrice.getEnCours());
	        calculatrice.clearEnCours();
	        interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
	    }

	    System.out.println("Bouton pressé : " + buttonIndex);
	}

	private void effectuerOperationSelonIndex(int buttonIndex) {
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
	}
}