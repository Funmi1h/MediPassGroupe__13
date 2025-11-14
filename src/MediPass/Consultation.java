package Medipass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Consultation {
	Scanner sc = new Scanner(System.in);
	
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
	
	private int IdConsultation;
	private LocalDateTime dateHeure;
	private String motif;
	private Patient patient;
	private ProfesionelDeSante PDS;
	private statuts statut;
	private List<String> observations;
	 
	
	public Consultation(int IdConsultation, LocalDateTime dateHeure, String motif, Patient patient, ProfesionelDeSante PDS){
		this.IdConsultation = IdConsultation;
		this.dateHeure = dateHeure;
		this.motif = motif;
		this.patient = patient;
		this.PDS = PDS;
		this.statut = statuts.PLANIFIEE; //Par défaut
		this.observations = new ArrayList<>(); 
	}
	
	public int getidConsultation() {
		return this.IdConsultation;
	}
	
	public LocalDateTime getdateHeure() {
		return this.dateHeure;
	}
	
	public String getmotif() {
		return this.motif;
	}
	
	public Patient getpatient() {
		return this.patient;
	}
	
	public ProfesionelDeSante getPDS() {
		return this.PDS;
	}
	
	public statuts getstatut() {
		return this.statut;
	}
	
	public List<String> getobservations(){
		return this.observations;
	}
	
	
	//Ajouter des observations
	public void ajouterobservations() {
		System.out.print("Observations: \n");
		String obs = sc.nextLine();
		if(!obs.isEmpty()){
			observations.add(obs);
			System.out.println("Observations ajoutée.")
		}
		else {
			System.out.println("Aucune observation.");
		}
	}
	
	//Modifier statut
	public void modifierstatut(statuts Nstatut){
		if (Nstatut != null){
			this.statut = Nstatut;
			System.out.println("Statut mis à jour: " + this.statut);
		}
	}
	
	//Modifier motif
	public void modifiermotif(String Nmotif){
		if(Nmotif != null && !Nmotif.isEmpty()){
			this.motif = Nmotif;
			System.out.println("Motif modifié avec succès.");
		}
		
	}
	

}
