package calculatricemodel;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatriceModel {
	Stack <Double> accumulateur = new Stack<>();
	Stack <Double> operande = new Stack<>();
	String enCours="";
	
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
	
	
	public String getEnCours() {
		return enCours;
	}
	
	public void ajoutEnCours(String element) {
		enCours +=element;
	}
	
	public void clearEncours() {
		enCours = "";
	}
	
	
}








