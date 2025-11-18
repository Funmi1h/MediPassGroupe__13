package MediPass;

public class Patient extends Utilisateur {
    
    
    public Patient (String n, String p, char[]mdp){
        super(
            n, 
            p, 
            "PATIENT",
            mdp, 
            RoleConfig.getDroitsPourRole("PATIENT")
            );

    }
}
