package model;

import java.util.EmptyStackException;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface{
	private String accu = "";

	public Stack <Double> operande = new Stack<>();
	String enCours = "";
	Double enCoursD = 0.0;
	
	public void add() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1+operande2;
	            operande.push(resultat);
	            accu = resultat.toString();
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour l'addition.");
	        }
	}
	
	public void substract() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1-operande2;
	            operande.push(resultat);
	            accu = resultat.toString();
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la soustraction.");
	        }
	}

	
	public void multiply() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            Double resultat = operande1*operande2;
	            operande.push(resultat);
	            accu = resultat.toString();
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la multiplication.");
	        }
	}
	
	public void divide() {
	        if (operande.size() >= 2) {
	            Double operande2 = operande.pop();
	            Double operande1 = operande.pop();
	            
	            if (operande2 != 0) {
	                Double resultat = operande1 / operande2;
	                operande.push(resultat);
	                accu = resultat.toString();
	            } else {
	                throw new ArithmeticException("Attention : Division par 0");
	            }
	        } else {
	            throw new IllegalArgumentException("Pas assez d'opérandes pour la division.");
	        }
	}

	
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
	        System.err.println("Erreur d'opposition : " + e.getMessage());
	    }
	}

	
	public void push(Double element) {
		operande.push(element);
	}
	
	public void pop() {
	    if (!operande.isEmpty()) {
	        Double element = operande.pop();
	        enCours = element.toString();
	    }
	}

	
	
	public void clear() {
		accu = "";
		operande.clear();
	}
	
	public void ajoutAccumulateur(Double element) {
		accu = element.toString();
	}
	
	public String getEnCours() {
		if (enCours.length()==0) {
			return "0.0";
		}
		else {
		return enCours;}
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

	public void drop() {
		operande.pop();
	}
	
	public String getAccu() {
		return accu;
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






