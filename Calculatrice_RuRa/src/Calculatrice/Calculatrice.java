package Calculatrice;

import java.util.Stack;

class Calculatrice {
	Stack <Double> accumulateur = new Stack<>();
	Stack <Double> operande = new Stack<>();
	
	public void multiplication (){
		if (accumulateur.empty()){
			accumulateur.push(operande.pop()*operande.pop());
		}
		else {
			accumulateur.push(accumulateur.peek()*operande.pop());
	}
}
}
