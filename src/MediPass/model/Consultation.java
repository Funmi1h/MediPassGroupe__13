package MediPass.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Consultation {

	private String idConsultation;
	private String idDossier;  
	private LocalDateTime dateHeure;
	private String motif;
	private Patient patient;
	private ProfessionnelSante pds;
	private statuts statut;
	private List<String> observations;
	
	public enum statuts {
		PLANIFIEE,
		EN_ATTENTE,
		EN_COURS,
		TERMINEE,
		ANNULEE,
		NON_HONOREE,
		REPORTEE,
		FACTUREE,
	}
	
	
	
	 
	
	public Consultation(String idConsultation, String idDossier, LocalDateTime dateHeure, String motif, Patient patient, ProfessionnelSante pds){
		this.idConsultation = idConsultation;
		this.idDossier = idDossier;
		this.dateHeure = dateHeure;
		this.motif = motif;
		this.patient = patient;
		this.pds = pds;
		this.statut = statuts.PLANIFIEE; //Par défaut
		this.observations = new ArrayList<>(); 
	}
	
	public String getIdConsultation() {
		return this.idConsultation;
	}

	public void setIdConsultation(String idConsultation) {
		this.idConsultation = idConsultation;
	}
	
	public String getIdDossier() {
		return this.idDossier;
	}

	public void setIdDossier(String idDossier) {
		this.idDossier = idDossier;
	}
	
	public LocalDateTime getDateHeure() {
		return this.dateHeure;
	}
	
	public String getMotif() {
		return this.motif;
	}
	
	public Patient getPatient() {
		return this.patient;
	}
	
	public ProfessionnelSante getPds() {
		return this.pds;
	}
	
	public statuts getStatut() {
		return this.statut;
	}
	
	public List<String> getObservations(){
		return this.observations;
	}
	
	//Modifier statut
	public void modifierStatut(statuts Nstatut){
		if (Nstatut != null){
			this.statut = Nstatut;
			System.out.println("Statut mis à jour: " + this.statut);
		}
	}
	
	//Modifier motif
	public void modifierMotif(String Nmotif){
		if(Nmotif != null && !Nmotif.isEmpty()){
			this.motif = Nmotif;
			System.out.println("Motif modifié avec succès.");
		}
		
	}
	

}
