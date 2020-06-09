package model;

import java.io.Serializable;

public class Section implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int temps;
	String solution;
	String texteOccultee;
	
	//constructeurs

	public Section(int temps, String solution) {
		this.temps = temps;
		this.solution = solution;
		genererTextOcculte();
	}

	private void genererTextOcculte() {

		texteOccultee = solution.replaceAll("[a-zA-Z0-9]", "#");

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

	@Override
	public String toString() {
		return "Section{" +
				"temps=" + temps +
				", solution='" + solution + '\'' +
				", texteOccultee='" + texteOccultee + '\'' +
				'}';
	}
}
