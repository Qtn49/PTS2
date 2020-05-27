package model;

import javafx.scene.control.TextArea;

public class Section {

	
	int temps, numero;
	TextArea solution;
	String texte;
	
	//constructeurs
	
	public Section() {
		super();
		this.temps = 0 ;
		this.numero = numero + 1;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TextArea getSolution() {
		return solution;
	}

	public void setSolution(TextArea solution) {
		this.solution = solution;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
}
