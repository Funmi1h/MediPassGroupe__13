package MediPass;

import java.util.ArrayList;
import java.util.List;

import MediPass.Antecedants;
import MediPass.Consultation;

public class Patient extends Utilisateur {
    
    private int idDossier;
	private String numeroDossier;
	private int age;
	private double taille;
	private double poids;

	private List<Consultation> consultations = new ArrayList<>();
	private List<Antecedants> antecedents = new ArrayList<>();
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
	public String getNumeroDossier() {
		return numeroDossier;
	}

	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getTaille() {
		return taille;
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

	public List<Antecedants> getAntecedents() {
		return antecedents;
	}
    // Relation: ajouter un antécédent pour ce patient
	public void ajouterAntecedent(Antecedants a) {
		if (a != null) {
			antecedents.add(a);
		}
	}

	// Relation: ajouter une consultation pour ce patient
	public void Faire_des_consultations(Consultation consultation) {
		if (consultation != null) {
			consultations.add(consultation);
		}
	}

	// Retourne l'historique des consultations sous forme de texte
	public String ConsulterHistoriqueConsultation() {
		StringBuilder sb = new StringBuilder();
		if (consultations.isEmpty()) {
			sb.append("Aucune consultation pour ce patient.\n");
		} else {
			sb.append("Historique des consultations:\n");
			for (Consultation c : consultations) {
				sb.append("- ID: ").append(c.getidConsultation())
				  .append(" | Motif: ").append(c.getmotif())
				  .append(" | Date: ").append(c.getdateHeure())
				  .append("\n");
			}
		}
		return sb.toString();
	}

	// Retourne le dossier patient (attributs + antécédents)
	public String ConsulterDossier() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dossier Patient:\n");
		sb.append("Numero: ").append(numeroDossier).append("\n");
		sb.append("Age: ").append(age).append("\n");
		sb.append("Taille: ").append(taille).append("\n");
		sb.append("Poids: ").append(poids).append("\n");
		sb.append("Antécédents:\n");
		if (antecedents.isEmpty()) {
			sb.append("  Aucun antécédent enregistré.\n");
		} else {
			for (Antecedants a : antecedents) {
				sb.append("  - ").append(a.getType()).append(": ")
				  .append(a.getDescription()).append("\n");
			}
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Patient [numeroDossier=" + numeroDossier + ", age=" + age + ", taille=" + taille + ", poids=" + poids + "]";
	}

}

