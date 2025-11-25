package services;

import MediPass.Utilisateur;

public class ServiceAuthentification {
	

	public Utilisateur authentifier(String identifiant, char[] motDePasse) throws Exception {
	    
	    // 1. Tente de récupérer l'utilisateur par identifiant (via UtilisateurDAO)
	    Utilisateur user = utilisateurDAO.trouverParIdentifiant(identifiant);
	    
	    // 2. Vérification du mot de passe (via une fonction de hachage)
	    if (user != null && PasswordUtils.verifier(motDePasse, user.getMotDePasseHash())) {
	        return user; // Succès de l'authentification
	    }
	     
	    throw new Exception("Identifiant ou mot de passe incorrect.");
	}

}
