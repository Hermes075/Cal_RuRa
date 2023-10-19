package application;

import calculatricemodel.CalculatriceModel;
import application.Main;
import java.util.Arrays;
import java.util.List;


public class SampleController {
	String chiffreEnCours = new String();
	public void handleButtonClick(int buttonIndex) {
		//Création d'un objet calculatrice
		CalculatriceModel calculatrice = new CalculatriceModel();
		Main main = new Main();
		
		//Liste des indexs de boutons correspondants à des chiffres sur l'interface
		List<Integer> indexChiffres = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		for (int index : indexChiffres) {
			if (index == buttonIndex) {
				calculatrice.ajoutEnCours(Integer.toString(index));
				calculatrice.ajoutPile((double) index);
				main.setEcranText(calculatrice.getEnCours());
				//Afficher enCours: il faut faire une méthode afficher dans Main
			}
		
		//On clique sur le bouton +
		if (buttonIndex == 10) {
			calculatrice.addition();
		}
			
		}
		System.out.println("Bouton pressé : " + buttonIndex);
        }
}

