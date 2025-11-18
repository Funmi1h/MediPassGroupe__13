package MediPass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DossierModicalM {
    private final String idDossier;
    private final String dateCreation;

    public DossierModicalM(){
        this.idDossier = "DOSSIER-";
        LocalDate dateJour = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Pour avoir 16//11/2025 pour la date
        this.dateCreation = dateJour.format(format);
    }

    

    
}
