package MediPass;

import java.time.LocalTime;
import java.util.UUID;

public class Creneau {
    private final String idCreneau;
    private final String jourSemaine;
    private final LocalTime heureDebut;
    private LocalTime heureFin;

   
    //constructeur
    public Creneau (String jourSemaine, LocalTime heureDebut, LocalTime heureFin){
        this.idCreneau = UUID.randomUUID().toString();//Crée un identifiant unique de 128bits et le transforme en chaine de caracteres
        this.jourSemaine =jourSemaine.toUpperCase();
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;

        // etre sur que l'heure de fin est apres l'heure de début 
        if (heureFin.isBefore(heureDebut) || heureFin.equals(heureDebut)){
            throw new IllegalArgumentException("\t L'heure de fin doit être postérieure à l'heure de début. \n");
        }
    }

    // les getters 

    public  String getIdCreneau(){
        return idCreneau;
    }

    public LocalTime getHeureDebut(){
        return heureDebut;
    }

    public LocalTime getHeureFin(){
        return heureFin;
    }

    public String getJourSemaine(){
        return jourSemaine;
    }

}
