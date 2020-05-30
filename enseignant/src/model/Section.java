package model;

import javafx.scene.control.TextArea;

public class Section {

	
	int temps;
	String solution;
	String texteOccultee;
	
	//constructeurs
	
	public Section() {
		super();
		this.temps = 0 ;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getTexteOccultee() {
		return texteOccultee;
	}

	public void setTexteOccultee(String texteOccultee) {
		this.texteOccultee = texteOccultee;
	}
}
