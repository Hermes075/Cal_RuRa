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
		
		//Liste des indexs de boutons correspondants à des chiffres sur l'interface
		// traitement des boutons
		List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		for (int index : indexChiffres) {
			if (index == buttonIndex) {
				calculatrice.ajoutEnCours(Integer.toString(index));
				calculatrice.ajoutPile((double) index);
				
				interfaceCalculatrice.updateLabel(calculatrice.getEnCours()); // pour mettre à jour l'interface de la calculatrice
				
			}
		
		//On clique sur le bouton +
		if (buttonIndex == 10) {
			calculatrice.addition();
		}
			
		}
		System.out.println("Bouton pressé : " + buttonIndex);
        }
}

