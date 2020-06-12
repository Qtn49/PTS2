package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Exercice implements Serializable {

	private ArrayList<Section> sections;
	private LinkedList<String> aide;
	private String consigne;
	private boolean modeEvaluation;
	private boolean limiteTps;
	private boolean affichageSolution;
	private boolean motIncomplet;
	private boolean sensibiliteCasse;
	private int temps;
	private int tempsTotal;
	private boolean fini;
	private Section sectionActuelle;
	

	//constructeurs

	public Exercice(String consigne, boolean modeEvaluation, boolean limiteTps) {
		sections = new ArrayList<>();
		aide = new LinkedList<>();
		this.consigne = consigne;
		this.modeEvaluation = modeEvaluation;
		this.limiteTps = limiteTps;
		
	}

	public void demarrerChrono (Section section) {

		sectionActuelle = section;
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (temps < tempsTotal * 60) {
				
					System.out.println("exercice : " + temps++);
					if (sectionActuelle.isFini()) {
						sectionActuelle = sections.get(sections.indexOf(sectionActuelle) + 1 % sections.size());
						System.out.println("fini !!!");
					}else
						sectionActuelle.demarrerChrono();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
				setFini(true);
				
			}
		};
		
//		if ()
		
		new Thread(runnable).start();

	}

	public void ajouteSection (int temps, String texte) {

		tempsTotal += temps;
		sections.add(new Section(temps, texte));

	}

	public void ajouteAide (String aide) {

		this.aide.add(aide);

	}
	
	public void changerChronoSection (Section section) {
		
		if (sectionActuelle != null) {
			
			sectionActuelle.pause();
			section.demarrerChrono();
			
		}
		
	}

	public void addSection (int index, Section section) {
		sections.add(index, section);
	}

	public Section getSection (int index) {
		return sections.get(index - 1);
	}

	public int nbSections () {
		return sections.size();
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

	public boolean isFini() {
		return fini;
	}

	public void setFini(boolean fini) {
		this.fini = fini;
	}

	public Section getSectionActuelle() {
		return sectionActuelle;
	}

	public void setSectionActuelle(Section sectionActuelle) {
		this.sectionActuelle = sectionActuelle;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Exercice{" +
				"sections=" + sections +
				", aide=" + aide +
				", consigne='" + consigne + '\'' +
				", modeEvaluation=" + modeEvaluation +
				", limiteTps=" + limiteTps +
				", affichageSolution=" + affichageSolution +
				", motIncomplet=" + motIncomplet +
				", sensibiliteCasse=" + sensibiliteCasse +
				'}';
	}

}
