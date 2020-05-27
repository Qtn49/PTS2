package model;

public class Exercice {

	int temps, nbSection;
	String titre, aide, consigne;

	//constructeurs

	public Exercice( String titre) {
		super();
		this.temps = 0;
		this.titre = titre;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAide() {
		return aide;
	}

	public void setAide(String aide) {
		this.aide = aide;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}




}
