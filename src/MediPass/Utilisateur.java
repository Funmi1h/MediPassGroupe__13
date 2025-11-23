package MediPass;

import java.util.Base64;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.Arrays;

public abstract class Utilisateur {
	Scanner scanner = new Scanner(System.in);
	private final String idUtilisateur;
	private String nom;
	private String prenom;
	private String role;
	private String motDePasseHash;
	private Set<Droit> droitAcces;
	//La variable ci dessous est pour forcer l'utilisateur a changer son mot de passe a la premiere connexion
	private boolean doitChangerMdp; 
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
		this.motDePasseHash = hashMotDePasse(mdp);
		droitSet = new HashSet<>();
		this.droitAcces= droitSet;
		this.doitChangerMdp = true;
	
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
			algo.update(new String(mdp).getBytes(StandardCharsets.UTF_8));
			byte[] bytes = algo.digest();
			hashMdp = Base64.getEncoder().encodeToString(bytes);
			
			
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException ("Erreur: Algorithme de hachage SHA-256 non disponible", e);
		}finally {
			Arrays.fill(mdp, (char) 0);
		}
		return hashMdp;
		
		
	}
	
	
	public void authentifier() {
		System.out.println("\t ********** Se connecter **********\n");
		char[] motDePasseSaisi = {};
		String saisie;
		do {
			System.out.println("\t Entrez votre mot de passe: \n");
			saisie = scanner.nextLine();
			
			if(saisie.trim().isEmpty()) {
				System.out.println("\t ❌❌❌ Le mot de passe ne peut pas être vide ❌❌❌\n");
				saisie = null;
			}else {
				motDePasseSaisi = saisie.toCharArray();
			}
		}while (saisie == null);
		
		if(hashMotDePasse(motDePasseSaisi).equals(motDePasseHash) ){
			// afficher l'interface appropriée au role de l'utilisateur

			// vérifier si c'est la premiere connexion et le forcer a changer si c'est le cas 
			if(doitChangerMdp == true){
				System.out.println("\t C'est votre première connexion: vous devez créer un nouveau mot de passe. \n ");
				changerMotPasse();
			}
		}else {
			System.out.println("❌❌❌ Vos identifiants sont incorrects ! ❌❌❌");			
		}
		
		
;		
		
	}
		
	public void changerMotPasse() {
		System.out.println("\t **********  Changer de mot de passe ********** \n");
		
		String saisie;
		char[] ancienMdp = null;
		char [] nouveauMdp = null;
		do {
			System.out.println("\t Votre mot de passe actuel: \t");
			saisie = scanner.nextLine();
			
			if(saisie.trim().isEmpty()) {
				System.out.println("\t ❌❌❌ Le mot de passe ne peut pas être vide ❌❌❌ \n");
			}else {
				 ancienMdp = saisie.toCharArray();
				 if (! (hashMotDePasse(ancienMdp) == motDePasseHash) ){
					System.out.println("\t ❌❌❌ Ancien mot de passe incorrect. ❌❌❌");
					ancienMdp = null;
				 }
			}
		}while ( ancienMdp == null || !(hashMotDePasse(ancienMdp) == motDePasseHash) );
		
		do {
			System.out.println("\t Votre nouveau mot de passe: \t");
			saisie = scanner.nextLine();
			
			if(saisie.trim().isEmpty()) {
				System.out.println("\t  ❌❌❌ Le mot de passe ne peut pas être vide ❌❌❌ \n");
				saisie = null;
			}else {
				 nouveauMdp = saisie.toCharArray();
				 if(motDePasseHash == hashMotDePasse(nouveauMdp)){
					System.out.println("\t ❌❌❌ L'ancien mot de passe et le nouveau sont identiques. ❌❌❌\n");
					nouveauMdp = null;
			}
		}
	}while (nouveauMdp == null);

		System.out.println("✅✅✅ Mot de passe changer avec succès ✅✅✅ \n");
		this.motDePasseHash = hashMotDePasse(nouveauMdp);
		if(doitChangerMdp == true){
			doitChangerMdp = false;
		}
		
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
	public String getMotDePasseHash() {
		return motDePasseHash;
	}
	
	public boolean getDoitChangerMdp(){
		return doitChangerMdp;
	}

}