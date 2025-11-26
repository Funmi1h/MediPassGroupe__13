package services;
import java.sql.SQLException;
import java.util.Map;

import MediPass.UtilisateurDAO;


public class ServiceStatistiques {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final PatientDAO patientDAO = new PatientDAO();
    private final ProfessionnelSanteDAO proSanteDAO = new ProfessionnelSanteDAO();
    private final ConsultationDAO consultationDAO = new ConsultationDAO();

    // --- (1) STATISTIQUES GÉNÉRALES ---
    public Map<String, Integer> obtenirStatsGenerales() throws SQLException {
        Map<String, Integer> stats = new java.util.HashMap<>();
        
        stats.put("TotalUtilisateurs", utilisateurDAO.compterTousLesUtilisateurs());
        stats.put("TotalPatients", patientDAO.compterTousLesPatients()); 

        return stats;
    }

    /**
     * Calcule le nombre de professionnels de santé pour chaque spécialité.
     * Nécessite un appel SQL comme: SELECT specialite, COUNT(*) FROM ProfessionnelSante GROUP BY specialite
     */
    public Map<String, Integer> compterProSanteParSpecialite() throws SQLException {
        // Cette logique doit être implémentée dans ProfessionnelSanteDAO
        return proSanteDAO.compterParSpecialite(); 
    }

    /**
     * Calcule le nombre de consultations par statut (PLANIFIEE, TERMINEE, ANNULEE).
     * Nécessite un appel SQL comme: SELECT statut, COUNT(*) FROM Consultation GROUP BY statut
     */
    public Map<String, Integer> compterConsultationsParStatut() throws SQLException {
        // Cette logique doit être implémentée dans ConsultationDAO
        return consultationDAO.compterParStatut();
    }
}