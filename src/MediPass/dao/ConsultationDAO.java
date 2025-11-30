package MediPass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import MediPass.model.Consultation;

public class ConsultationDAO {
	private Connection conn;

    public ConsultationDAO(Connection conn) {
        this.conn = conn;
    }

    // 1 CREATE : Ajouter une consultation
    public void ajouterConsultation(Consultation c) throws SQLException {
        String sql = "INSERT INTO Consultation (idConsultation, idDossier, idProfessionnelSante, dateConsultation, motif, statut) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, c.getIdConsultation());
        stmt.setString(2, c.getIdDossier());
        stmt.setString(3, c.getPds().getIdUtilisateur()); // à adapter selon ta classe
        stmt.setString(4, c.getDateHeure().toString());
        stmt.setString(5, c.getMotif());
        stmt.setString(6, c.getStatut().name());
        stmt.executeUpdate();
    }

    // 2 READ : Récupérer une consultation par ID
    public Consultation getConsultationParId(String id) throws SQLException {
        String sql = "SELECT * FROM Consultation WHERE idConsultation = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, String.valueOf(id));
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
            return c;
        }
        return null;
    }

    // 3 UPDATE : Modifier une consultation
    public void modifierConsultation(Consultation c) throws SQLException {
        String sql = "UPDATE Consultation SET idDossier = ?, idProfessionnelSante = ?, dateConsultation = ?, motif = ?, statut = ? WHERE idConsultation = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, c.getIdDossier());
        stmt.setString(2, c.getPds().getIdUtilisateur());
        stmt.setString(3, c.getDateHeure().toString());
        stmt.setString(4, c.getMotif());
        stmt.setString(5, c.getStatut().name());
        stmt.setString(6, c.getIdConsultation());
        stmt.executeUpdate();
    }

    // 4 DELETE : Supprimer une consultation
    public void supprimerConsultation(String id) throws SQLException {
        String sql = "DELETE FROM Consultation WHERE idConsultation = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.executeUpdate();
    }

    // 5 READ ALL : Récupérer toutes les consultations
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
            c.setIdConsultation(rs.getString("idConsultation"));
            c.setIdDossier(rs.getString("idDossier"));
            c.modifierStatut(Consultation.statuts.valueOf(rs.getString("statut")));
            consultations.add(c);
        }

        return consultations;
    }

}

