package MediPass;

import java.util.ArrayList;
import java.util.List;


public class ProfessionnelSante extends Utilisateur {
	private String specialite;
	private List<Creneau> planningDisponibilite;
	
	//Constructeur
	public ProfessionnelSante( String n, String p, char[] mdp, String specialite) {
		

		super(
			n,
			p,
			"ProfessionnelSante",
			mdp,
			RoleConfig.getDroitsPourRole("Professionnel_Sante")
			);
		this.specialite = specialite;
		this.planningDisponibilite = new ArrayList<>();

	}

		//Pour ajouter un nouveau creneau
		public void ajouterDisponibilite(Creneau nouveauCreneau){
				this.planningDisponibilite.add(nouveauCreneau);
			}
		
			// Supprimer un creneau 
		public void supprimerDisponibiite(String idCreneau){
			this.planningDisponibilite.removeIf(c -> c.getIdCreneau().equals(idCreneau));
		}


		public Patient creerPatient(String nom, String prenom, char[] mdpInitial){
			if(!this.aDroit(Droit.CREER_COMPTE_PATIENT)){
				System.err.println("\t ****** Erreur: Vous n'avez pas le droit de créer un patient. ******\n");
				return null;
			}
			Patient nouveauPatient = new Patient(nom, prenom, mdpInitial);

			// Enregistrer le patient dans le base de données
			return nouveauPatient;

		}

		public Patient rechercherPatient(String nom, String prenom){
			if(!aDroit(Droit.RECHERCHER_PATIENT)){
				System.err.println();
				
				}
			//Logique pour rechercher quelque chose dans la base de données avec les parametres nom et prenom 
			Patient patient = null; 

				
			return patient ;

		}

		public DossierMedical creerDossierMedical(){
			if(!aDroit(Droit.CREER_DOSSIER_MEDICAL)){
				System.err.println("\t ****** Erreur vous ne pouvez créer de dossier médical ");

			}
			//Prendre des infos du constructeur de DossierMedical 
			//créer un nouveau dossier l'enregistrer dans la base de donnees et le return
			return null;


		}

		public void  consulterDossierMedical(


		){

		}
		public void programmerConsultation(){
			
		}

		public  void  enregistrerAntecedents (){

		}

		public void enregistrerConsultation(){
			
		}

		public String getSpecialite(){
			return specialite;
		}
}
