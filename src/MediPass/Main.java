package MediPass;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
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
	    }
	}

