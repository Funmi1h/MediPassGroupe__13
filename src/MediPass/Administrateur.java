package MediPass;

import java.util.HashSet;

import java.util.Set;

import java.util.Scanner;

public class Administrateur extends Utilisateur {
	
	private Scanner scanner = new Scanner(System.in);

    // Déclaration et initialisation des droits
    private Set<Droit> droitAcces = new HashSet<>() ;{{
    	droitAcces.add(Droit.CREER_COMPTE_PROFESSIONNEL);
    	droitAcces.add(Droit.MODIFIER_COMPTE_PROFESSIONNEL);
    	droitAcces.add(Droit.SUSPENDRE_COMPTE_PROFESSIONNEL);
    	droitAcces.add(Droit.SUPPRIMER_COMPTE_PROFESSIONNEL);
    }};

    public Administrateur() {
        super();
    }

    // Redéfinition de aDroit pour utiliser le Set local
   
    public boolean aDroit(Droit droitRequis) {
        return droitAcces.contains(droitRequis);
    }

    // Création d'un compte professionnel
    public void creerCompteProfessionnel(Set<Utilisateur> baseUtilisateurs) {
        if (!aDroit(Droit.CREER_COMPTE_PROFESSIONNEL)) {
            System.out.println("Vous n'avez pas le droit de créer un compte professionnel !");
            return;
        }

        System.out.println(" CREATION D'UN COMPTE PROFESSIONNEL");
        System.out.print("id : ");
        String id=scanner.nextLine();
        System.out.print("Nom : ");
        String nom=scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom=scanner.nextLine();
        String role=scanner.nextLine();
        System.out.print("role : ");
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        Utilisateur nouveauPro = new professionneldesante();
        nouveauPro.hashMotDePasse(motDePasse);

        baseUtilisateurs.add(nouveauPro);
        System.out.println("Compte professionnel créé (nom/prénom non définis) !");
    }

    // Modification d'un compte professionnel
    public void modifierCompteProfessionnel(Utilisateur u) {
        if (!aDroit(Droit.MODIFIER_COMPTE_PROFESSIONNEL)) {
            System.out.println("Vous n'avez pas le droit de modifier ce compte professionnel !");
            return;
        }

        System.out.println(" MODIFICATION DU COMPTE PROFESSIONNEL");
        System.out.print("id : ");
         String id=scanner.nextLine(); 
        System.out.print("Nouveau nom : ");
        String nom=scanner.nextLine();
        System.out.print("Nouveau prénom : ");
        String prenom=scanner.nextLine();
        String role=scanner.nextLine();
        System.out.print("role : ");
        System.out.print("Nouveau mot de passe : ");
        String newmotdepasse = scanner.nextLine();
        

        u.hashMotDePasse(newmotdepasse);

        System.out.println("Le compte professionnel a été modifié avec succès !");
        System.out.println("Attention : nom et prénom non modifiés (Utilisateur ne fournit pas de setters).");
    }

    // Suppression d'un compte professionnel
    public void supprimerCompteProfessionnel(Utilisateur u, Set<Utilisateur> baseUtilisateurs) {
        if (!aDroit(Droit.SUPPRIMER_COMPTE_PROFESSIONNEL)) {
            System.out.println("Vous n'avez pas le droit de supprimer ce compte professionnel !");
            return;
        }

        System.out.println("SUPPRESSION DU COMPTE PROFESSIONNEL");
        System.out.print("Voulez-vous vraiment supprimer ce compte ? (oui/non) : ");
        String rep = scanner.nextLine();

        if (rep.equalsIgnoreCase("oui")) {
            baseUtilisateurs.remove(u);
            System.out.println("Le compte professionnel a été supprimé avec succès !");
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    // Suspension d'un compte professionnel
    public void suspendreCompteProfessionnel(Utilisateur u) {
        if (!aDroit(Droit.SUSPENDRE_COMPTE_PROFESSIONNEL)) {
            System.out.println("Vous n'avez pas le droit de suspendre ce compte professionnel !");
            return;
        }

        System.out.println("SUSPENSION DU COMPTE PROFESSIONNEL");
        System.out.println("Le compte professionnel a été suspendu .");
    }
}

