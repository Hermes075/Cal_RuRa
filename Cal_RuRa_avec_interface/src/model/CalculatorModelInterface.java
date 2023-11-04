package model;

import java.util.Stack;

public interface CalculatorModelInterface {
	
	public void addition();
	public void soustraction();
	public void multiplication();
	public void division();
	public void opposition();
	public void ajoutPile(Double element);
	public Double enlevePile(Stack<Double> pile );
	public void clearPile();
	public void ajoutAccumulateur(Double element);
	public String getEnCours();
	public void ajoutEnCours(String element);
	public void clearEnCours();
	public void swap();


}
