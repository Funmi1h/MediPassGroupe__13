/*

package MediPass;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		Connection conn = SQLiteManager.connect();
		 
	Scanner scanner = new Scanner(System.in);

	        // 1️ Créer l'administrateur
	        char[] mdpAdmin = {'1','2','3','4'};
	        Administrateur admin = new Administrateur ("Adebiaye", "Ronice", mdpAdmin);

	         boolean quitter = false;

	        while (!quitter) {
	            System.out.println("\n===== MENU ADMINISTRATEUR =====");
	            System.out.println("1 - Créer un compte professionnel");
	            System.out.println("2 - Modifier un compte professionnel");
	            System.out.println("3 - Supprimer un compte professionnel");
	            System.out.println("4 - Suspendre un compte professionnel");
	            System.out.println("5 - Quitter");
	            System.out.print("Choisissez une option : ");

	            String choix = scanner.nextLine();

	            switch (choix) {
	                case "1":
	                    admin.creerCompteProfessionnel();
	                    break;
	                case "2":
	                    admin.modifierCompteProfessionnel(); 
	                    break;
	                case "3":
	                    admin.supprimerCompteProfessionnel(); 
	                    break;
	                case "4":
	                    admin.suspendreCompteProfessionnel(); 
	                    break;
	                case "5":
	                    System.out.println("Au revoir !");
	                    quitter = true;
	                    break;
	                default:
	                    System.out.println("Option invalide !");
	            }
	        }

	        scanner.close();
			SQLiteManager.closeConnection(conn);
	    }
		
	}
*/

package MediPass;

import java.sql.SQLException;
import java.util.UUID;

import MediPass.model.Administrateur;

public class Main {

    public static void main(String[] args) {
        
        SQLiteManager.initialiserBaseDeDonnees();
        
        creerAdministrateurInitial();
        MediPassIHM ihm = new MediPassIHM();
        ihm.demarrer(); 
    }

    /**
     * Vérifie si l'administrateur initial existe et le crée sinon.
     */
    private static void creerAdministrateurInitial() {
        
        
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        
        try {
            // Tente de trouver l'utilisateur "admin"
            if (utilisateurDAO.trouverParIdentifiant("MainAdmin") == null) {
                
                System.out.println("⚠️ Création de l'administrateur initial...");
                final char[] mdpInitial = "pass123".toCharArray();
                
                // Création de l'objet Administrateur
                // Le mot de passe sera haché dans le constructeur/DAO
                Administrateur adminInitial = new Administrateur("Main", "Admin", mdpInitial);
                
                
                // Insertion dans la BDD (via le DAO)
                utilisateurDAO.insererUtilisateur(adminInitial); 
                
                System.out.println("✅ Administrateur 'admin' créé avec le mot de passe temporaire 'pass123'.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur  lors de la création de l'administrateur initial: " + e.getMessage());
        }
    }
}
