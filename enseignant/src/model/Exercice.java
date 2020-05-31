package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Exercice {

	private ArrayList<Section> sections;
	private String cheminRessource;
	private LinkedList<String> aide;
	private String consigne;
	private boolean modeEvaluation;
	private boolean limiteTps;
	private boolean affichageSolution;
	private boolean motIncomplet;
	private boolean sensibiliteCasse;

	//constructeurs


	public Exercice(ArrayList<Section> sections, String cheminRessource, LinkedList<String> aide, String consigne, boolean modeEvaluation, boolean limiteTps) {
		this.sections = sections;
		this.cheminRessource = cheminRessource;
		this.aide = aide;
		this.consigne = consigne;
		this.modeEvaluation = modeEvaluation;
		this.limiteTps = limiteTps;
	}

	public void addSection (int index, Section section) {
		sections.add(index, section);
	}

	public Section getSection (int index) {
		return sections.get(index);
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}

	public String getCheminRessource() {
		return cheminRessource;
	}

	public void setCheminRessource(String cheminRessource) {
		this.cheminRessource = cheminRessource;
	}

	public LinkedList<String> getAide() {
		return aide;
	}

	public void setAide(LinkedList<String> aide) {
		this.aide = aide;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	public boolean isModeEvaluation() {
		return modeEvaluation;
	}

	public void setModeEvaluation(boolean modeEvaluation) {
		this.modeEvaluation = modeEvaluation;
	}

	public boolean isLimiteTps() {
		return limiteTps;
	}

	public void setLimiteTps(boolean limiteTps) {
		this.limiteTps = limiteTps;
	}

	public boolean isAffichageSolution() {
		return affichageSolution;
	}

	public void setAffichageSolution(boolean affichageSolution) {
		this.affichageSolution = affichageSolution;
	}

	public boolean isMotIncomplet() {
		return motIncomplet;
	}

	public void setMotIncomplet(boolean motIncomplet) {
		this.motIncomplet = motIncomplet;
	}

	public boolean isSensibiliteCasse() {
		return sensibiliteCasse;
	}

	public void setSensibiliteCasse(boolean sensibiliteCasse) {
		this.sensibiliteCasse = sensibiliteCasse;
	}
}
