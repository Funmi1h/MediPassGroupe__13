package MediPass;

import java.util.HashSet;
import java.util.Set;

import java.util.Scanner;

public abstract class Utilisateur {
	Scanner scanner = new Scanner(System.in);
	public enum Droit {
		//Droits de l'administrateur
		CREER_COMPTE_PROFESSIONNEL,
		MODIFIER_COMPTE_PROFESSIONNEL,
		SUSPENDRE_COMPTE_PROFESSIONNEL,
		SUPPRIMER_COMPTE_PROFESSIONNEL,
		
		// DROITS du professionnel de santé
		CREER_COMPTE_PATIENT,
		PROGRAMMER_CONSULTATION,
		CONSULTER_DOSSIERMEDICAL,
		AJOUTER_ANTECEDENT,
		MODIFIER_DOSSIER_PATIENT,
		
		
		// DROITS du patient 
		 CONSULTER_MON_DOSSIERMEDICAL,
		
		
	}

	private int id;
	private String nom;
	private String prenom;
	private String role;
	private String motDePasse;
	private Set<Droit> droitAcces = new HashSet<>();
	public boolean aDroit(Droit droitRequis) {
		if(droitAcces.contains(droitRequis))
			return true;
		else 
			return false;
	}
	
	public void hashMotDePasse(String mdp) {
		
		
	}
	public void authentifier() {
		System.out.println("\t Entrez votre mot de passe: \t");
		//Le mot de passe sra d'abord un char car peut etre immédiatement effacé e eviter fuite de mot de passe 
		// Etre sur que le motDePasse n'est pas null
		
		String motDePasseSaisi = scanner.nextLine();
		if(motDePasseSaisi == motDePasse) {
			// afficher l'interface appropriée au role de l'utilisateur
		}else {
			System.out.println("Vos identifiants sont incorrects");			
		}
		
		
;		
		
	}
		
	public void changerMotPasse() {
		System.out.println("\t Votre mot de passe actuel: \t");
		String ancienMotDePasse = scanner.nextLine();
		System.out.println("\t Votre nouveau mot de passe: \t");
		
		
		
	}
	
	


}