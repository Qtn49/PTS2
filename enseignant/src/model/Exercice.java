package model;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;

public class Exercice {

	ArrayList<Section> sections;
	MediaPlayer ressource;
	ListView<String> aide;
	TextArea consigne;
	String titre;

	//constructeurs

	public Exercice( String titre) {
		super();
		sections = new ArrayList<>();
		this.titre = titre;
	}

	public MediaPlayer getRessource() {
		return ressource;
	}

	public void setRessource(MediaPlayer ressource) {
		this.ressource = ressource;
	}

	public ListView<String> getAide() {
		return aide;
	}

	public void setAide(ListView<String> aide) {
		this.aide = aide;
	}

	public TextArea getConsigne() {
		return consigne;
	}

	public void setConsigne(TextArea consigne) {
		this.consigne = consigne;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
