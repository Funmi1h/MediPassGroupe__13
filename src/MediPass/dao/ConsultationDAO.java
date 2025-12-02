package MediPass.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import MediPass.model.Consultation;

public class ConsultationDAO {
    private Connection conn;

    public ConsultationDAO(Connection conn) {
        this.conn = conn;
    }

    // Ajouter une consultation
    public void ajouterConsultation(Consultation c) throws SQLException {
        String sql = "INSERT INTO Consultation (idConsultation, idDossier, dateConsultation, motif, idProfessionnelSante, statut) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, c.getIdConsultation());
        stmt.setString(2, c.getIdDossier());
        stmt.setString(3, c.getDateHeure().toString());
        stmt.setString(4, c.getMotif());
        stmt.setString(5, c.getIdProfessionnelSante().getIdUtilisateur());
        stmt.setString(6, c.getStatut().name());
        stmt.executeUpdate();
    }

    // Récupérer une consultation par ID
    public Consultation getConsultationParId(String id) throws SQLException {
        String sql = "SELECT * FROM Consultation WHERE idConsultation = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateConsultation"));
            Consultation c = new Consultation(
                rs.getString("idConsultation"),
                rs.getString("idDossier"),
                date,
                rs.getString("motif"),
                null,
                null
            );
            c.modifierStatut(Consultation.statuts.valueOf(rs.getString("statut")));
            c.setObservations(getObservationsParConsultation(c.getIdConsultation()));
            return c;
        }
        return null;
    }

    // Modifier une consultation
    public void modifierConsultation(Consultation c) throws SQLException {
        String sql = "UPDATE Consultation SET idDossier = ?, idProfessionnelSante = ?, dateConsultation = ?, motif = ?, statut = ? WHERE idConsultation = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, c.getIdDossier());
        stmt.setString(2, c.getIdProfessionnelSante().getIdUtilisateur());
        stmt.setString(3, c.getDateHeure().toString());
        stmt.setString(4, c.getMotif());
        stmt.setString(5, c.getStatut().name());
        stmt.setString(6, c.getIdConsultation());
        stmt.executeUpdate();
    }

    // Supprimer une consultation
    public void supprimerConsultation(String id) throws SQLException {
        String sql = "DELETE FROM Consultation WHERE idConsultation = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.executeUpdate();
    }

    // Récupérer toutes les consultations
    public List<Consultation> getToutesLesConsultations() throws SQLException {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM Consultation";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateConsultation"));
            Consultation c = new Consultation(
                rs.getString("idConsultation"),
                rs.getString("idDossier"),
                date,
                rs.getString("motif"),
                null,
                null
            );
            c.modifierStatut(Consultation.statuts.valueOf(rs.getString("statut")));
            c.setObservations(getObservationsParConsultation(c.getIdConsultation()));
            consultations.add(c);
        }
        return consultations;
    }

    // Ajouter une observation
    public void ajouterObservation(String idConsultation, String contenu) throws SQLException {
        String sql = "INSERT INTO Observation (idConsultation, contenu) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idConsultation);
            stmt.setString(2, contenu);
            stmt.executeUpdate();
        }
    }

    // Récupérer les observations liées à une consultation
    public List<String> getObservationsParConsultation(String idConsultation) throws SQLException {
        List<String> observations = new ArrayList<>();
        String sql = "SELECT contenu FROM Observation WHERE idConsultation = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idConsultation);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                observations.add(rs.getString("contenu"));
            }
        }
        return observations;
    }
}