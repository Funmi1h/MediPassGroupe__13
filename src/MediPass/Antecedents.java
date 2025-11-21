package g13;

public class Antecedents {
	private int idAntecedents;
	private String type;
	private String description;
	private boolean estConnecte;
	private boolean estMalade;
	
	public Antecedents(final int idAntecedents,final String type,final String description,final boolean estConnecte,final boolean estMalade) {
		this.idAntecedents=idAntecedents;
		this.type=type;
		this.description=description;
		this.estConnecte=estConnecte;
		this.estMalade=estMalade;
	}
	public int getIdAntecedents() {
		return idAntecedents;
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description; 
	}
	public boolean getEstConnecte() {
		return estConnecte; 
	}
	public boolean getEstMalade() {
		return estMalade; 
	}
	
	public void setIdAntecedents(int idAntecedents) {
		this.idAntecedents=idAntecedents;
	}
	
	public void setType(String type) {
		this.type=type;
		
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public void setEstConnecte(boolean estConnecte) {
		this.estConnecte=estConnecte;
	}
	public void setEstMalade(boolean estMalade) {
		this.estMalade=estMalade; 
	}
	
	public String afficher() {
		String message;
		if(estConnecte) {
			 // System.out.println();
			  message="Voici votre antecedents: \n" + "id Antecedents- " + this.idAntecedents +"\n" + "Type -" + this.type +"\n" + "Description- " + this.description;
		}
		else {
			//System.out.println("Connectez vous d'abord");
			 message="Connectez vous d'abord";
		}
		return message; 
	}
	
	
	
	public boolean estAllergie() {
		//un boolean est allergie retournant true ou false ou soit une fonction disons patient consult qui verifie si le patient est malade
		//System.out.println("Vraie");
		if(estMalade) {
			System.out.println("vous etes malade");
			
		}
		else {
			System.out.println("non pas malade");
		}
		return estMalade; 
	
		 
	}

	

}
