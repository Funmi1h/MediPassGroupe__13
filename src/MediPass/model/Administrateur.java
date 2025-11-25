package MediPass.model;

import java.sql.SQLException;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import MediPass.Droit;
import MediPass.RoleConfig;
import MediPass.Utilisateur;
import services.ServiceStatistiques;

import java.util.Scanner;






 public class Administrateur extends Utilisateur {
	  private Scanner scanner = new Scanner(System.in);
	  
	    private Set<Utilisateur> utilisateurs = new HashSet<>();
	    protected Set<Droit> droitAcces = new HashSet<>();
	    
	    private final ServiceStatistiques statsService = new ServiceStatistiques();

	    // Constructeur
	    public Administrateur(String nom, String prenom, char[] mdp) {
	        super(nom, prenom, "ADMINISTRATEUR", mdp, RoleConfig.getDroitsPourRole("ADMINISTRATEUR"));
	    }

	    public void ajouterUtilisateur(Utilisateur u) {
	        utilisateurs.add(u);
	    }

	    public boolean aDroit(Droit droitRequis) {
	        return droitAcces.contains(droitRequis);
	    }

	  //creatiOn de compte professionnel
	    public void creerCompteProfessionnel() {
	        if (!aDroit(Droit.CREER_COMPTE_PROFESSIONNEL)) {
	            System.out.println("Vous n'avez pas le droit de créer un compte professionnel .");
	            return;
	        }

	        System.out.println("--- CREATION D'UN PROFESSIONNEL ---");

	        String nom = saisirNomOuPrenom("Nom : ");
	        String prenom = saisirNomOuPrenom("Pr�nom : ");
	        char[] mdp = saisirMotDePasse("Mot de passe : ");
	        String specialite = saisirNomOuPrenom("Sp�cialit� : ");

	        ProfessionnelSante pro = new ProfessionnelSante(nom, prenom, mdp, specialite);
	        utilisateurs.add(pro);
	        System.out.println("Compte créé. ID = " + pro.getIdUtilisateur()  + ". \n Votre mot de passe temporaire est " + new String(mdp));
	    }

	   //modification de compte professionnel
	    public void modifierCompteProfessionnel() {
	        if (!aDroit(Droit.MODIFIER_COMPTE_PROFESSIONNEL)) {
	            System.out.println("Vous n'avez pas le droit.");
	            return;
	        }

	        System.out.println(" MODIFICATION ");

	        String id = saisirChampObligatoire("ID du compte : ");
	        Utilisateur user = trouverUtilisateur(id);

	        if (user == null) {
	            System.out.println("Introuvable.");
	            return;
	        }

	        String newNom = saisirNomOuPrenom("Nouveau nom : ");
	        String newPrenom = saisirNomOuPrenom("Nouveau pr�nom : ");

	        try {
	            java.lang.reflect.Field nomField = Utilisateur.class.getDeclaredField("nom");
	            java.lang.reflect.Field prenomField = Utilisateur.class.getDeclaredField("prenom");
	            nomField.setAccessible(true);
	            prenomField.setAccessible(true);

	            nomField.set(user, newNom);
	            prenomField.set(user, newPrenom);
	        } catch (Exception e) {
	            System.out.println("Erreur lors de la modification : " + e.getMessage());
	            return;
	        }

	       
	        System.out.println("Modifi� avec succ�s.");
	    }

	    //supprimercompte
	    public void supprimerCompteProfessionnel() {
	        if (!aDroit(Droit.SUPPRIMER_COMPTE_PROFESSIONNEL)) {
	            System.out.println("Interdit.");
	            return;
	        }

	        System.out.println(" SUPPRESSION ");

	        String id = saisirChampObligatoire("ID : ");
	        Utilisateur u = trouverUtilisateur(id);

	        if (u == null) {
	            System.out.println("Introuvable.");
	            return;
	        }

	        utilisateurs.remove(u);
	        System.out.println("Supprim� avec succ�s.");
	    }

	   //suspendre compte
	    public void suspendreCompteProfessionnel() {
	        if (!aDroit(Droit.SUSPENDRE_COMPTE_PROFESSIONNEL)) {
	            System.out.println("Interdit.");
	            return;
	        }

	        System.out.println("=== SUSPENSION ===");

	        String id = saisirChampObligatoire("ID : ");
	        Utilisateur u = trouverUtilisateur(id);

	        if (u == null) {
	            System.out.println("Introuvable.");
	            return;
	        }

	        System.out.println("Compte suspendu (simulation).");
	    }

	   
	    private String saisirNomOuPrenom(String message) {
	        while (true) {
	            System.out.print(message);
	            String saisie = scanner.nextLine().trim();
	            if (saisie.isEmpty()) {
	                System.out.println("Erreur : le champ ne peut pas �tre vide.");
	                continue;
	            }
	            if (saisie.matches("[0-9]+")) {
	                System.out.println("Erreur : le champ ne peut pas �tre un nombre.");
	                continue;
	            }
	            return saisie;
	        }
	    }

	    private char[] saisirMotDePasse(String message) {
	        while (true) {
	            System.out.print(message);
	            String saisie = scanner.nextLine();
	            if (!saisie.isEmpty()) {
	                return saisie.toCharArray();
	            }
	            System.out.println("Erreur : mot de passe vide.");
	        }
	    }

	    private String saisirChampObligatoire(String message) {
	        while (true) {
	            System.out.print(message);
	            String s = scanner.nextLine().trim();
	            if (!s.isEmpty()) return s;
	            System.out.println("Erreur : ce champ est obligatoire.");
	        }
	    }

	    private Utilisateur trouverUtilisateur(String id) {
	        for (Utilisateur u : utilisateurs) {
	            if (u.getIdUtilisateur().equals(id)) return u;
	        }
	        return null;
	    }
	    
	    
	    public void afficherStatistiquesSysteme() {
	        System.out.println("\n--- 📈 RAPPORT STATISTIQUES SYSTÈME ---");
	        
	        try {
	            // 1. Statistiques générales (Patients et Utilisateurs)
	            Object statsService;
				Map<String, Integer> statsGenerales = statsService.obtenirStatsGenerales();
	            
	            System.out.println("\n--- 👥 Utilisateurs et Patients ---");
	            System.out.println("------------------------------------------");
	            System.out.println("Total des Utilisateurs (tous rôles confondus ) : " + statsGenerales.get("TotalUtilisateurs"));
	            System.out.println("Total des Patients enregistrés : " + statsGenerales.get("TotalPatients"));
	            
	            // 2. Statistiques des Professionnels de Santé par spécialité
	            Map<String, Integer> statsProSante = statsService.compterProSanteParSpecialite();
	            
	            System.out.println("\n--- 👨‍⚕️ Professionnels par Catégorie ---");
	            System.out.println("------------------------------------------");
	            if (statsProSante.isEmpty()) {
	                System.out.println("Aucun professionnel de santé enregistré.");
	            } else {
	                statsProSante.forEach((specialite, count) -> {
	                    System.out.println(" - " + specialite + " : " + count);
	                });
	            }

	            // 3. Statistiques des Consultations (Exemple : par statut ou par mois)
	            // Pour l'exemple, nous allons compter les consultations terminées vs. planifiées
	            Map<String, Integer> statsConsultations = statsService.compterConsultationsParStatut();
	            
	            System.out.println("\n--- 📅 État des Consultations ---");
	            System.out.println("------------------------------------------");
	            statsConsultations.forEach((statut, count) -> {
	                System.out.println(" - " + statut + " : " + count);
	            });
	            
	            // (Optionnel) Ajout des consultations par période
	            // List<Map<String, Object>> consultationsParMois = statsService.obtenirConsultationsParPeriode(12);
	            // System.out.println("\nConsultations dans les 12 derniers mois...");
	            
	        } catch (SQLException e) {
	            System.err.println("❌ Erreur BDD : Impossible d'obtenir les statistiques. " + e.getMessage());
	        }
	}	  		