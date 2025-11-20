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
				System.err.println("****** Erreur: Vous n'avez pas le droit de créer un patient. ******\n");
				return null;
			}
			Patient nouveauPatient = new Patient(nom, prenom, mdpInitial);

			// Enregistrer le patient dans le base de données
			return nouveauPatient;

		}

		public void rechercherPatient(){

		}

		public void creerDossierMedical(){

		}

		public void  consulterDossierMedical(){

		}

		public void enregistrerConsultation(){
			
		}
}
