package MediPass;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RoleConfig {
    public static Set<Droit> getDroitsPourRole(String role){
        Set<Droit> droits = new HashSet<>();
        switch (role.toUpperCase()) {
            case "ADMINISTRATEUR":
                droits.addAll(Arrays.asList(
                    Droit.CREER_COMPTE_PROFESSIONNEL,
                    Droit.MODIFIER_COMPTE_PROFESSIONNEL,
                    Droit.SUSPENDRE_COMPTE_PROFESSIONNEL,
                    Droit.SUPPRIMER_COMPTE_PROFESSIONNEL,
                    Droit.CONSULTER_STATISTIQUES_SYSTEME));
                
                break;
            case "PROFESSIONNEL_SANTE":
                droits.addAll(Arrays.asList(
                    Droit.CREER_COMPTE_PATIENT,
                    Droit.PROGRAMMER_CONSULTATION,
                    Droit.CONSULTER_DOSSIERMEDICAL,
                    Droit.AJOUTER_ANTECEDENT,
                    Droit.MODIFIER_DOSSIER_PATIENT,
                    Droit.GERER_PLANNING
                ));
                break;
            
            case "PATIENT":
                droits.add(Droit.CONSULTER_MON_DOSSIER_MEDICAL);
                break;
        
            default:
                break;
        }
        return droits;

    }
    
}
