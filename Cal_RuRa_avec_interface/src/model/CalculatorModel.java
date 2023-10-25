package model;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface{
	Stack <Double> accumulateur = new Stack<>();
	Stack <Double> operande = new Stack<>();
	Double enCours=(double) 0;
	Boolean saisieApresVirgule = false;
	Integer compteurApresVirgule = 0;
	Boolean enCoursPositif = true;
	
	public void addition() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1+operande2;
	            operande.push(resultat);
	            accumulateur.clear();
	            accumulateur.push(resultat);
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour l'addition.");
	        }
	}
	
	public void soustraction() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1-operande2;
	            operande.push(resultat);
	            accumulateur.clear();
	            accumulateur.push(resultat);
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la soustraction.");
	        }
	}

	
	public void multiplication() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1*operande2;
	            operande.push(resultat);
	            accumulateur.clear();
	            accumulateur.push(resultat);
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la multiplication.");
	        }
	}
	
	public void division() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            
	            if (operande2 != 0) {
	                Double resultat = operande1 / operande2;
	                operande.push(resultat);
	                accumulateur.clear();
	                accumulateur.push(resultat);
	            } else {
	                throw new ArithmeticException("Atention : Division par 0");
	            }
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la division.");
	        }
	}

	
	public void opposition() {
	    try {
	        if (operande.empty()) {
	            throw new EmptyStackException();
	        } else {
	            Double element = operande.pop();
	            operande.push(-element);
	            accumulateur.push(-element);
	        }
	    } catch (EmptyStackException e) {
	        System.err.println("Erreur d'opposition : " + e.getMessage());
	    }
	}

	
	public void ajoutPile(Double element) {
		operande.push(element);
	}
	
	public Double enlevePile(Stack<Double> pile ) {
		return operande.pop();
	}
	
	
	public void clearPile() {
		accumulateur.clear();
		operande.clear();
	}
	
	public void ajoutAccumulateur(Double element) {
		accumulateur.push(element);
	}
	
	
	public Double getEnCours() {
		return enCours;
	}
	
	public void ajoutEnCours(Double element) {
		if (saisieApresVirgule == false) {
			enCours = 10*enCours + element;
		}
		else {
			// Calculer la puissance de 10 nécessaire pour le nouvel élément
	        double puissance = Math.pow(10, -String.valueOf(element).length() + 2 - compteurApresVirgule);

	        // Ajouter l'élément après la virgule en le divisant par la puissance de 10 en vérifiant le signe
	        if (enCoursPositif == true) {
	        	enCours += element * puissance;
	        }
	        else {
	        	enCours -= element * puissance;
	        }
	        compteurApresVirgule ++;
		}
	}
	
	public void clearEnCours() {
		enCours = (double) 0;
	}
	
	public void inverseEnCours() {
		enCours = - enCours;
		enCoursPositif = !enCoursPositif;
	}
	
	public Double getResultat() {
		Double resultat = 0.0;
	    for (Double operation : accumulateur) {
	        resultat += operation;
	    }
	    return resultat;
	}
	
	public Boolean getBoolean(){
		return saisieApresVirgule;	
	}
	
	public void inverserBoolean(){
		saisieApresVirgule = !saisieApresVirgule;	
	}
	
	public void reinitBoolean() {
		saisieApresVirgule = false;
	}
	
	public void reinitCompteur() {
		compteurApresVirgule = 0;
	}
	
	public void reinitBoolPositif() {
		enCoursPositif = true;
	}
	
	public void enCoursPourcent(){
		enCours =  enCours/100;
	}
	
	public String getOperandeAsString() {
	    StringBuilder builder = new StringBuilder();
	    for (Double value : operande) {
	        builder.append(value);
	        builder.append("; ");
	    }
	    return builder.toString();
	}
}