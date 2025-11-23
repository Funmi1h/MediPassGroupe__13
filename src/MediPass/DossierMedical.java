package MediPass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList ;
import java.util.List ;
import java.util.Date ;

public class DossierMedical {
    private String idDossier; //Identifiant unique du Dossier_medical
    private String dateCreation ; //date d'ouverture du dossier

    private ArrayList<Consultation> consultations; //Liste des consultations associées au dossier médical
    private ArrayList<Antecedents> antecedents; //Liste des antecedents associées au dossier médical
                 
                 
                 //----CONSTRUCTEUR-----
    

    public DossierMedical(){
        this.idDossier = "DOSSIER-";
        LocalDate dateJour = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Pour avoir 16//11/2025 pour la date
        this.dateCreation = dateJour.format(format);
        this.antecedents = new ArrayList<Antecedents>() ;
        this.consultations = new ArrayList<Consultation>() ;
    }
                //----Methodes pour ajouter un antecedent----
     public void ajouterAntecedent(Antecedents antecedent) {
        if (antecedent!= null) {
        this.antecedents.add(antecedent) ;
        System.out.println("Antécédent ajouté au dossier medical."+ this.idDossier);
    }
    }
                //----Methodes pour ajouter une consultation----
     public void ajouterConsultation(Consultation consultation) {
        if (consultation!= null) {
        this.consultations.add(consultation) ;
        System.out.println("Consultation ajoutée au dossier medical."+ this.idDossier);
    } 
    }
                    //----methodes pour consulter la liste des conlsultations et antecedents----
    public ArrayList<Antecedents> Antecedents() {
        return this.antecedents ;
    }
    public ArrayList<Consultation> Consultations() {
        return this.consultations ;
    }
    //----methode pour chercher une consultation par son ID/date----
    public Consultation searchConsultation(String idConsultation) {
        for (Consultation c : this.consultations) {
            /*/
            if (c.getidConsultation().equals(idConsultation)) {
                return c ; // correspond a la premiere consultation trouvee
            }*/
        }
        return null ; // Consultation non trouvée
    }

}