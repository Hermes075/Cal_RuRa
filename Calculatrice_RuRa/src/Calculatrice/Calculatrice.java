package Calculatrice;

import java.util.Stack;

class Calculatrice {
	Stack <Double> accumulateur = new Stack<>();
	Stack <Double> operande = new Stack<>();
	
	public void addition () {
		if (accumulateur.empty()) {
			accumulateur.push(operande.pop()+operande.pop());
		}
		else {
			accumulateur.push(accumulateur.peek()+operande.pop());
		}
	}
	
	public void soustraction () {
		if (accumulateur.empty()) {
			accumulateur.push(-operande.pop()+operande.pop());
		}
		else {
			accumulateur.push(-accumulateur.peek()+operande.pop());
		}
	}
	
	public void multiplication (){
		if (accumulateur.empty()){
			accumulateur.push(operande.pop()*operande.pop());
		}
		else {
			accumulateur.push(accumulateur.peek()*operande.pop());
	}
	}
	
	public void division() {
		if (accumulateur.empty()){
			accumulateur.push((1/operande.pop())*operande.pop());
		}
		else {
			accumulateur.push((1/accumulateur.peek())*operande.pop());
	}
	}
	
	public void opposition() {
		if (accumulateur.empty()){
			accumulateur.push(-operande.pop());
		}
		else {
			accumulateur.push(-accumulateur.peek());
	}
	}
}
