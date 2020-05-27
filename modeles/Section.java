package model;

public class Section {

	
	int temps, numero;
	String texte, solution;
	
	//constructeurs
	
	public Section(String texte) {
		super();
		this.temps = 0 ;
		this.numero = numero + 1;
		this.texte = "";
	}

	//getter and setter
	
	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	
	
	
	
	
}
