package MediPass;
import java.util.Scanner;
import MediPass.model.ProfessionnelSante;
import services.ServiceAuthentification;
import MediPass.model.Patient;



public class MediPassIHM {
	
	
	// Dans la classe MediPassIHM.java

	private Utilisateur utilisateurConnecte = null;
	private final ServiceAuthentification authService = new ServiceAuthentification();
	
	Scanner scanner = new Scanner(System.in);

	public void demarrer() {
	    while (true) { // Boucle principale de l'application
	        
	        // 1. Gérer l'authentification
	        utilisateurConnecte = null;
	        while (utilisateurConnecte == null) {
	            utilisateurConnecte = gererSaisieConnexion();
	        }

	        // 2. Vérification du Mot de Passe temporaire
	        if (utilisateurConnecte.getDoitChangerMdp() == true) { 
	            System.out.println("ATTENTION : Votre mot de passe est temporaire. Vous devez le changer maintenant.");
	            
	            // Si le changement échoue, l'utilisateur doit se déconnecter et réessayer
	            boolean succesChangement = gererChangementMdp(utilisateurConnecte); 
	            
	            if (succesChangement) {
	                // Mettre à jour la BDD pour indiquer que le MDP n'est plus temporaire (doitChangerMdp = 0)
	                utilisateurDAO.mettreAJourStatutMdp(utilisateurConnecte.getIdUtilisateur(), 0); 
	                utilisateurConnecte.setDoitChangerMdp(false); 
	            } else {
	                System.out.println("❌❌ Échec du changement de mot de passe. Déconnexion.");
	                continue; // Retourne à la boucle de connexion
	            }
	        }
	        
	        // 3. Redirection vers le menu spécifique (pour tous les rôles)
	        afficherMenuPrincipal(utilisateurConnecte); 
	    }
	}

	

	// Méthode de saisie (simplement pour la clarté)
	private Utilisateur gererSaisieConnexion() {
	    System.out.println("\n--- CONNEXION ---");
	    System.out.print("Identifiant : ");
	    
		String id = scanner.nextLine();
	    System.out.print("Mot de passe : ");
	    char[] mdp = scanner.nextLine().toCharArray();
	    
	    try {
	        Utilisateur user = authService.authentifier(id, mdp);
	        System.out.println("Connexion réussie !");
	        return user;
	    } catch (Exception e) {
	        System.err.println("Erreur de connexion : " + e.getMessage());
	        return null;
	    } finally {
	        // Sécurité: vider le mot de passe
	        java.util.Arrays.fill(mdp, (char) 0);
	    }
	}
	

	private void afficherMenuPrincipal(Utilisateur user) {
	    System.out.println("\nConnexion réussie ! Bienvenue, " + user.getPrenom() + ".");
	    
	    // Le rôle détermine l'interface
	    if (user.getRole() == "ADMINISTRATEUR") {
	        menuAdministrateur();
	    } else if (user instanceof ProfessionnelSante) {
	        menuProfessionnelSante((ProfessionnelSante) user);
	    } else if (user instanceof Patient) {
	        menuPatient((Patient) user);
	    } else {
	        System.out.println("Rôle non reconnu. Déconnexion.");
	    }
	}
	
	
	//menuAdministrateur
	


	private void menuAdministrateur() {
	    boolean enCours = true;
	    while (enCours) {
	        System.out.println("\n--- Menu Administrateur ---");
	        System.out.println("1. Afficher les statistiques système");
	        System.out.println("2. Gérer les comptes utilisateurs"); 
	        System.out.println("3. Déconnexion");

	        System.out.print("Choisissez une option : ");
	        String choix = scanner.nextLine();

	        switch (choix) {
	            case "1":
	                // Utiliser le ServiceStatistiques que nous avons défini
	                // afficherStats(); 
	                break;
	            case "2":
	            	// Aff
	            case "3":
	                enCours = false;
	                break;
	            default:
	                System.out.println("Option invalide.");
	        }
	    }
	    System.out.println("Déconnexion réussie.");
	    utilisateurConnecte = null;
	}
	
	
	

	private void menuProfessionnelSante(ProfessionnelSante medecin) {
	    boolean enCours = true;
	    while (enCours) {
	        System.out.println("\n--- Menu Médecin (" + medecin.getSpecialite() + ") ---");
	        System.out.println("1. Créer un nouveau patient");
	        System.out.println("2. Consulter un dossier patient");
	        System.out.println("3. Ajouter une consultation");
	        System.out.println("4. Déconnexion");

	        System.out.print("Choisissez une option : ");
	        String choix = scanner.nextLine();
	        
	        switch (choix) {
	            case "1":
	                // Appeler une méthode qui gère la saisie et l'appel à medecin.creerPatient(...)
	                gererCreationPatient(medecin); 
	                break;
	            case "2":
	                // Appeler une méthode pour rechercher dans le PatientDAO
	                // gererConsultationDossier();
	                break;
	            case "4":
	                enCours = false;
	                break;
	            default:
	                System.out.println("Option invalide.");
	        }
	    }
	    System.out.println("Déconnexion réussie.");
	    utilisateurConnecte = null; // Réinitialiser pour une nouvelle connexion
	}
	
	
	 void menuPatient(Patient patient) {
	    boolean enCours = true;
	    
	    System.out.println("\n--- Bienvenue, Patient " + patient.getPrenom() + " --- \n");
	    
	    while (enCours) {
	        System.out.println("\n--- Menu Patient ---");
	        System.out.println("1. Consulter mon dossier médical");
	        System.out.println("2. Mettre à jour mon mot de passe");
	        System.out.println("3. Déconnexion");

	        System.out.print("Choisissez une option : ");
	        String choix = scanner.nextLine();
	        
	        switch (choix) {
	            case "1":
	                gererConsultationDossierPatient(patient);
	                break;
	            case "2":
	                gererChangementMotDePasse(patient); 
	                break;
	            case "3":
	                enCours = false; // Sort de la boucle
	                break;
	            default:
	                System.out.println("Option invalide. Veuillez réessayer.");
	        }
	    }
	    
	    System.out.println("Déconnexion réussie. À bientôt.");
	    // Après déconnexion, vous devez réinitialiser la variable d'utilisateur si nécessaire
	    // Ex: utilisateurConnecte = null;
	}
	
	
	
	
	public void gererCreationPatient(ProfessionnelSante user) {
		
	}
	
	
	public void gererConsultationDossier() {
		
	}
	
	
	public void gererAjouterConsultation() {
		
	}
	
	
	

}
