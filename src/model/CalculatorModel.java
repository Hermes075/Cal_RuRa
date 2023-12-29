package model;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface{
	private String accu = "";

	public Stack <Double> operande = new Stack<>();
	String enCours = "";
	Double enCoursD = 0.0;
	
	// Addition
	public void add() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1+operande2;
	            operande.push(resultat);
	            accu = resultat.toString();
	        } else {
	            throw new IllegalArgumentException(" Not enough operands for addition.");
	        }
	}
	
	// Substraction
	public void substract() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1-operande2;
	            operande.push(resultat);
	            accu = resultat.toString();
	        } else {
	            throw new IllegalArgumentException("Not enough operands for substraction.");
	        }
	}

	// Multiplication
	public void multiply() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1*operande2;
	            operande.push(resultat);
	            accu = resultat.toString();
	        } else {
	            throw new IllegalArgumentException("Not enough operands for multiplication.");
	        }
	}
	
	// Division
	public void divide() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            
	            if (operande2 != 0) {
	                Double resultat = operande1 / operande2;
	                operande.push(resultat);
	                accu = resultat.toString();
	            } else {
	                throw new ArithmeticException("Warning: Division by zero.");
	            }
	        } else {
	            throw new IllegalArgumentException("Not enough operands for division.");
	        }
	}

	// Opposition
	public void opposite() {
	    try {
	        if (operande.empty()) {
	            throw new EmptyStackException();
	        } else {
	            Double element = -operande.pop();
	            operande.push(element);
	            accu = element.toString();
	        }
	    } catch (EmptyStackException e) {
	        System.err.println("Opposition error: " + e.getMessage());
	    }
	}

	// STACK IMPLEMENTATION
	// Stack push
	public void push(Double element) {
		operande.push(element);
	}
	
	// Stack pop
	public void pop() {
	    if (!operande.isEmpty()) {
	        Double element = operande.pop();
	        enCours = element.toString();
	    }
	}

	
	// Stack clear
	public void clear() {
		accu = "";
		operande.clear();
	}
	
	public void ajoutAccumulateur(Double element) {
		accu = element.toString();
	}
	
	public String getEnCours() {
		if (enCours.length()==0) {
			return "0";
		}
		else {
		return enCours.toString();}
	}
	
	public void clearEnCours() {
		enCours = "";
	}
	
	public void inverseEnCours() {
		if (enCours!="") {
		enCoursD = Double.parseDouble(enCours);
		enCoursD = -enCoursD;
		enCours = enCoursD.toString();}
	}
	
	public String getResultat() {
		return accu;
	}
	
	// Exchange the two first elements of the list
	public void swap(){
		if (operande.size()>1){
		Double operande1 = operande.pop();
        Double operande2 = operande.pop();
        operande.add(operande1);
        operande.add(operande2);}
		else {
			throw new IllegalArgumentException("Not enough operands for swap.");	
		}
	}
	
	public void ajoutEnCours(String element) {
	    if (element.equals(".")) {
	        if (!enCours.contains(".")) {
	            enCours = enCours + element;
	        }
	    } else {
	        enCours = enCours + element;
	    }
	}

	public String getAccu() {
		return accu;
	}

	public void supprimer() {
	    if (!enCours.isEmpty()) {
	        enCours = enCours.substring(0, enCours.length() - 1);
	    }
	}

	// Allows to retrieve the operands by returning them 
	public List<Double> peek3() {
		int operandeSize = operande.size();
		List<Double> dernieresValeurs = new ArrayList<>();
		while ( operandeSize>0) {
			dernieresValeurs.add(operande.get(operandeSize - 1));
			operandeSize--;
		}
		return dernieresValeurs;
}
}