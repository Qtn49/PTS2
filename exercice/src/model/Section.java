package model;

import java.io.Serializable;

public class Section implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tempsMax;
	private int temps;
	private String solution;
	private String texteOccultee;
	private boolean pause;
	private boolean fini;
	
	//constructeurs

	public Section(int temps, String solution) {
		this.tempsMax = temps;
		this.solution = solution;
		genererTextOcculte();
	}

	// méthodes
	
	public void demarrerChrono () {
		
		if (temps < tempsMax * 60 && !pause) {
			
			System.out.println("Section : " + temps);
			temps++;
			
		}else
			fini = true;
		
	}
	
	public void pause () {
		pause = !pause;
	}
	
	private void genererTextOcculte() {

		texteOccultee = solution.replaceAll("[a-zA-Z0-9]", "#");

	}

	public int getTemps() {
		return tempsMax;
	}

	public void setTemps(int temps) {
		this.tempsMax = temps;
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

	public boolean isFini() {
		return fini;
	}

	public void setFini(boolean fini) {
		this.fini = fini;
	}

	@Override
	public String toString() {
		return "Section{" +
				"temps=" + tempsMax +
				", solution='" + solution + '\'' +
				", texteOccultee='" + texteOccultee + '\'' +
				'}';
	}
}
