package MediPass.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Consultation {

	private String IdConsultation;
	private String IdDossier;  
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
	
	
	
	 
	
	public Consultation(String IdConsultation, String IdDossier, LocalDateTime dateHeure, String motif, Patient patient, ProfessionnelSante pds){
		this.IdConsultation = IdConsultation;
		this.IdDossier = IdDossier;
		this.dateHeure = dateHeure;
		this.motif = motif;
		this.patient = patient;
		this.pds = pds;
		this.statut = statuts.PLANIFIEE; //Par défaut
		this.observations = new ArrayList<>(); 
	}
	
	public String getIdConsultation() {
		return this.IdConsultation;
	}

	public void setIdConsultation(String IdConsultation) {
		this.IdConsultation = IdConsultation;
	}
	
	public String getIdDossier() {
		return this.IdDossier;
	}

	public void setIdDossier(String IdDossier) {
		this.IdDossier = IdDossier;
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
