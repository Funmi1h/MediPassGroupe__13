package MediPass;

import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Base64;






 public class Administrateur extends Utilisateur {
	  private Scanner scanner = new Scanner(System.in);
	  
	    private Set<Utilisateur> utilisateurs = new HashSet<>();
	    protected Set<Droit> droitAcces = new HashSet<>();

	    // Constructeur
	    public Administrateur(String nom, String prenom, char[] mdp) {
	        super(nom, prenom, "ADMIN", mdp, new HashSet<>());

	        // Droits par défaut
	        droitAcces.add(Droit.CREER_COMPTE_PROFESSIONNEL);
	        droitAcces.add(Droit.MODIFIER_COMPTE_PROFESSIONNEL);
	        droitAcces.add(Droit.SUSPENDRE_COMPTE_PROFESSIONNEL);
	        droitAcces.add(Droit.SUPPRIMER_COMPTE_PROFESSIONNEL);
	    }

	    public void ajouterUtilisateur(Utilisateur u) {
	        utilisateurs.add(u);
	    }

	    public boolean aDroit(Droit droitRequis) {
	        return droitAcces.contains(droitRequis);
	    }

	  //creatin de compte professionnel
	    public void creerCompteProfessionnel() {
	        if (!aDroit(Droit.CREER_COMPTE_PROFESSIONNEL)) {
	            System.out.println("Vous n'avez pas le droit.");
	            return;
	        }

	        System.out.println("CREATION D'UN PROFESSIONNEL");

	        String nom = saisirNomOuPrenom("Nom : ");
	        String prenom = saisirNomOuPrenom("Prénom : ");
	        char[] mdp = saisirMotDePasse("Mot de passe : ");
	        String specialite = saisirNomOuPrenom("Spécialité : ");

	        ProfessionnelSante pro = new ProfessionnelSante(nom, prenom, mdp, specialite);
	        utilisateurs.add(pro);
	        System.out.println("Compte créé. ID = " + pro.getIdUtilisateur());
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
	        String newPrenom = saisirNomOuPrenom("Nouveau prénom : ");

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

	       
	        System.out.println("Modifié avec succès.");
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
	        System.out.println("Supprimé avec succès.");
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
	                System.out.println("Erreur : le champ ne peut pas être vide.");
	                continue;
	            }
	            if (saisie.matches("[0-9]+")) {
	                System.out.println("Erreur : le champ ne peut pas être un nombre.");
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
	}	  		