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
	public Double getEnCours();
	public void ajoutEnCours(Double element);
	public void clearEnCours();
	public void inverseEnCours();
	public Double getResultat();
	public Boolean getBoolean();
	public void inverserBoolean();
	public void reinitBoolean();
	public void reinitCompteur();
	public void reinitBoolPositif();
	public void enCoursPourcent();

}
