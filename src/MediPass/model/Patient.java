package MediPass;

import java.util.ArrayList;
import java.util.List;

import MediPass.Antecedents;
import MediPass.Consultation;

public class Patient extends Utilisateur {
    
    private int idDossier;
	private int age;
	private double taille;
	private double poids;

	private List<Consultation> consultations = new ArrayList<>();
	private List<Antecedents> antecedents = new ArrayList<>();
        //Constructeurs
	public Patient (String n, String p, char[]mdp){
        super(
            n, 
            p, 
            "PATIENT",
            mdp, 
            RoleConfig.getDroitsPourRole("PATIENT")
            );
    }

	// Getters et Setters
	


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getTaille() {
		return taille;
	}
	public int  getIdDossier() {
		return idDossier;
	}
	public void setTaille(double taille) {
		this.taille = taille;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public List<Antecedents> getAntecedents() {
		return antecedents;
	}
    // Relation: ajouter un antécédent pour ce patient
	public void ajouterAntecedent(Antecedents a) {
		if (a != null) {
			antecedents.add(a);
		}
	}

	// Relation: ajouter une consultation pour ce patient
	public void faire_des_consultations(Consultation consultation) {
		if (consultation != null) {
			consultations.add(consultation);
		}
	}

	// Retourne l'historique des consultations sous forme de texte
	public String consulterHistoriqueConsultation() {
		StringBuilder sb = new StringBuilder();
		if (consultations.isEmpty()) {
			sb.append("Aucune consultation pour ce patient.\n");
		} else {
			sb.append("Historique des consultations:\n");
			for (Consultation c : consultations) {
				sb.append("- ID: ").append(c.getI1dConsultation())
				  .append(" | Motif: ").append(c.getMotif())
				  .append(" | Date: ").append(c.getDateHeure())
				  .append("\n");
			}
		}
		return sb.toString();
	}

	// Retourne le dossier patient (attributs + antécédents)
	public String ConsulterDossier() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dossier Patient:\n");
		sb.append("Numero: ").append(idDossier).append("\n");
		sb.append("Age: ").append(age).append("\n");
		sb.append("Taille: ").append(taille).append("\n");
		sb.append("Poids: ").append(poids).append("\n");
		sb.append("Antécédents:\n");
		if (antecedents.isEmpty()) {
			sb.append("  Aucun antécédent enregistré.\n");
		} else {
			for (Antecedents a : antecedents) {
				sb.append("  - ").append(a.getType()).append(": ")
				  .append(a.getDescription()).append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Patient [numeroDossier=" + idDossier + ", age=" + age + ", taille=" + taille + ", poids=" + poids + "]";
	}

}

