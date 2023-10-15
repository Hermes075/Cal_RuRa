package Calculatrice;

import java.util.EmptyStackException;
import java.util.Stack;

public class Calculatrice {
	Stack <Double> accumulateur = new Stack<>();
	Stack <Double> operande = new Stack<>();
	
	public void addition() {
	    try {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1+operande2;
	            operande.push(resultat);
	            accumulateur.push(resultat);
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour l'addition.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.err.println("Erreur d'addition : " + e.getMessage());
	    }
	}


	
	public void soustraction() {
	    try {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1-operande2;
	            operande.push(resultat);
	            accumulateur.push(resultat);
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour l'addition.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.err.println("Erreur d'addition : " + e.getMessage());
	    }
	}

	
	public void multiplication() {
	    try {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1*operande2;
	            operande.push(resultat);
	            accumulateur.push(resultat);
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour l'addition.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.err.println("Erreur d'addition : " + e.getMessage());
	    }
	}
	
	public void division() {
	    try {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            
	            if (operande2 != 0) {
	                Double resultat = operande1 / operande2;
	                operande.push(resultat);
	                accumulateur.push(resultat);
	            } else {
	                throw new ArithmeticException("Division par 0");
	            }
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la division.");
	        }
	    } catch (IllegalArgumentException e) {
	        System.err.println("Erreur de division : " + e.getMessage());
	    } catch (ArithmeticException e) {
	        System.err.println("Erreur de division : " + e.getMessage());
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

	
	public void ajoutPile(Stack<Double> pile, Double element) {
		pile.push(element);
	}
	
	public Double elevePile(Stack<Double> pile ) {
		return pile.pop();
	}
	
	public void clearPile() {
		accumulateur.clear();
		operande.clear();
	}
	
}








