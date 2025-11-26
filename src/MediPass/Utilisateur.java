package MediPass;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public abstract class Utilisateur {
	Scanner scanner = new Scanner(System.in);
	private final String idUtilisateur;
	private String nom;
	private String prenom;
	private String role;
	private String motDePasse;
	private Set<Droit> droitAcces;
	public boolean aDroit(Droit droitRequis) {
		if(droitAcces.contains(droitRequis))
			return true;
		else 
			return false;
	}
	
	//Constructeurs 
	/*
	 * n = nom
	 * p = prenom 
	 * r = role
	 * mdp = motDePasse
	 * droitSet = droitAcces*/
	public Utilisateur(String n, String p, String r, char[] mdp, Set<Droit>droitSet) {
		this.idUtilisateur = UUID.randomUUID().toString();
		this.nom= n;
		this.prenom = p;
		this.role = r;
		this.motDePasse = hashMotDePasse(mdp);
		droitSet = new HashSet<>();
		this.droitAcces= droitSet;
	}
	
	/*
	 * Hasher le mot de passe en utilisant l'algorithme SHA-256
	 * A la fin on transforme le tableau de mot de passe 
	 * */
	public String hashMotDePasse(char[] mdp) {
		String hashMdp = null;
		
		try {
			
			//initialisation de l'agorithme SHA-256
			MessageDigest algo = MessageDigest.getInstance("SHA-256");
			String mdpString = new String(mdp);
			algo.update(mdpString.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = algo.digest();
			hashMdp = Base64.getEncoder().encodeToString(bytes);
			
			
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException ("Erreur: Algorithme de hachage SHA-256 non disponible", e);
		}finally {
			for(char c : mdp)
			{
				c = 0;
				}
		}
		return hashMdp;
		
		
	}
	public void authentifier() {
		System.out.println("********** Se connecter **********\n");
		char[] motDePasseSaisi = {};
		String saisie;
		do {
			System.out.println("\t Entrez votre mot de passe: \n");
			saisie = scanner.nextLine();
			
			if(saisie.trim().isEmpty()) {
				System.out.println("\t ERREUR: Le mot de passe ne peut pas être vide \n");
				saisie = null;
			}else {
				motDePasseSaisi = saisie.toCharArray();
			}
		}while (saisie == null);
		
		
		
		
		if(hashMotDePasse(motDePasseSaisi) == motDePasse) {
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
	
	
	
	//les getters 
	public String getIdUtilisateur(){
		return idUtilisateur;
	}
	
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;		
	}
	public String getRole() {
		return role;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	
}