package application;

import calculatricemodel.CalculatriceModel;
import application.Main;
import java.util.Arrays;
import java.util.List;


public class SampleController {
	private Interface interfaceCalculatrice;
	private CalculatriceModel calculatrice;
	
	String chiffreEnCours = new String();
	// on fait ici une association avec l'interface
	public void setInterface(Interface app) {
        this.interfaceCalculatrice = app;
    }
	
	public SampleController () {
		calculatrice = new CalculatriceModel();
	}
	
	
	public void handleButtonClick(int buttonIndex) {
		
		//Liste des indexs de boutons correspondants à des chiffres sur l'interface
		
		List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		for (int index : indexChiffres) {
			if (index == buttonIndex) {
				calculatrice.ajoutEnCours(Integer.toString(index));
				calculatrice.ajoutPile((double) index);
				
				interfaceCalculatrice.updateLabel(calculatrice.getEnCours());
				
			}
		
		//On clique sur le bouton +
		if (buttonIndex == 10) {
			calculatrice.addition();
		}
			
		}
		System.out.println("Bouton pressé : " + buttonIndex);
        }
}

