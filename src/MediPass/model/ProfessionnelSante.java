package MediPass;


import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import MediPass.Droit;
import MediPass.RoleConfig;


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


		public Patient creerPatient(){
			String nom;
			String prenom;
			char[] mdpInitial;
			if(!this.aDroit(Droit.CREER_COMPTE_PATIENT)){
				System.err.println("\t ****** Erreur: Vous n'avez pas le droit de créer un patient. ******\n");
				return null;
			}
			Patient nouveauPatient = new Patient(nom, prenom, mdpInitial);
			
	        System.out.println("Compte créé. ID = " + nouveauPatient.getIdUtilisateur()  + ". \n Votre mot de passe temporaire est " + new String(mdp));


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

		public void  consulterDossierMedical(/*params */){
			try {
				//rechercher le patient grace au parametres 
				Patient patient = patientDAO.trouverParIdentifiant(identifiant);
				if(patient == null) {
					System.out.println("❌ Patient introuvable avec cet identifiant.");
					return;
				}
				
				System.out.println("\n✅ Patient trouvé : " + patient.getPrenom() + " " + patient.getNom());
	            String idDossier = patient.getIdDossier();
	            // cahrger les consultations du dossier
	            List<Consultation> consultations = consultationDAO.trouverParIdDossier(idDossier);
	            
	            // afficher les infos de base du dossier
	            DossierMedical dossier = patient.getDossierMedical(); //La DAO charge le dossier
	            System.out.println("Dossier Médical N°: " + patient.getIdDossier());
	            System.out.println("Age: " + patient.getAge());
	            
	            
	            //Afficher historique
	            System.out.println("\n--- HISTORIQUE DES CONSULTATIONS ---");
	            if (consultations.isEmpty()) {
	                 System.out.println("Aucune consultation enregistrée.");
	            } else {
	                for (Consultation c : consultations) {
	                    System.out.println("-------------------------\n");
	                    System.out.println("N° : " + c.getIdConsultation());
	                    System.out.println("Date : " + c.getDateHeure());
	                    System.out.println("Motif : " + c.getMotif());
	                    List <String> observations = c.getObservations();
	                    if (observations != null && !observations.isEmpty()) {
	                        // Affichage de chaque observation sur une ligne séparée
	                        for (int i = 0; i < observations.size(); i++) {
	                            System.out.println(" - " + (i + 1) + ". " + observations.get(i));
	                        }
	                    } else {
	                        System.out.println(" - Aucune observation détaillée.");
	                    }
	                    
	                }
	            }
	            		
				
			}catch(SQLException e){
				System.out.println("Erreur de la base de données lors de la consultation");
				
			}
			

		}
		public void programmerConsultation(){
			System.out.println("\n ---Programmation de Consultation ---\n");
			Consultation consultationProgrammee = new Consultation(0, LocalDateTime dateHeure, String motif, Patient patient, ProfessionnelSante pds); 
			
			// enregistrer dans la BDD
			consultationDAO.insererConsultation(consultationProgramme);
				
		}

		public  void  enregistrerAntecedents (){

		}

		public void enregistrerConsultation(){
			
		}

		public String getSpecialite(){
			return specialite;
		}
}
