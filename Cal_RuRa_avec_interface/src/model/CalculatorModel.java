package model;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface{
	String accumulateur = "";
	public Stack <Double> operande = new Stack<>();
	String enCours = "";
	Double enCoursD = 0.0;
	
	public void addition() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1+operande2;
	            operande.push(resultat);
	            accumulateur = resultat.toString();
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
	            accumulateur = resultat.toString();
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
	            accumulateur = resultat.toString();
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
	                accumulateur = resultat.toString();
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
	            Double element = -operande.pop();
	            operande.push(element);
	            accumulateur = element.toString();
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
		accumulateur = "";
		operande.clear();
	}
	
	public void ajoutAccumulateur(Double element) {
		accumulateur = element.toString();
	}
	
	public String getEnCours() {
		return enCours;
	}
	
	public void clearEnCours() {
		enCours = "";
	}
	
	public void inverseEnCours() {
		enCoursD = Double.parseDouble(enCours);
		enCoursD = -enCoursD;
		enCours = enCoursD.toString();
	}
	
	public String getResultat() {
		return accumulateur;
	}
	
	public void swap(){
		if (operande.size()>1){
		Double operande1 = operande.pop();
        Double operande2 = operande.pop();
        operande.add(operande1);
        operande.add(operande2);}
		else {
			throw new IllegalArgumentException("Pas assez d'opérandes pour la swap");	
		}
	}
	
	public String getOperandeAsString() {
	    StringBuilder builder = new StringBuilder();
	    for (Double value : operande) {
	        builder.append(value);
	        builder.append("; ");
	    }
	    return builder.toString();
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
}


/*
	public List<Double> peek3() {
	    int accumulateurSize = accumulateur.size();
	    List<Double> lastThreeValues = new ArrayList<>();

	    // Ajouter des zéros pour remplir la liste si elle contient moins de 3 éléments
	    while (lastThreeValues.size() < 5) {
	        if (accumulateurSize > 0) {
	            lastThreeValues.add(accumulateur.get(accumulateurSize - 1));
	            accumulateurSize--;
	        } else {
	            lastThreeValues.add(0.0); // Ajouter un zéro
	        }
	    }

	    // Inverser la liste pour obtenir les valeurs dans l'ordre correct
	    Collections.reverse(lastThreeValues);
	    return lastThreeValues;
	}
<<<<<<< HEAD
	
	public void delete() {
		operande.pop();
	}


=======
>>>>>>> branch 'main' of https://github.com/Hermes075/Cal_RuRa.git
	
*/






