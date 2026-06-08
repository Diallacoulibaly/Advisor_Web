package main.java.model.DaoImplement;

import main.java.model.dao.CompetenceProjetDao;
import main.java.Database.ConnectBD;
import main.java.model.classes.CompetenceProjet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetenceProjetDaoImplement implements CompetenceProjetDao {

    @Override
    public boolean add_CP(CompetenceProjet cp) {
        String sql = "INSERT INTO CompetenceProjet (idCompetence, idProjet) VALUES (?, ?)";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cp.getCompetenceId());
            pstmt.setInt(2, cp.getIdProjet());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout CompetenceProjet : " + e.getMessage());
            return false;
        }
    }

    @Override
    public CompetenceProjet rech_CP(int idProjet, int idCompetence) {
        String sql = "SELECT * FROM CompetenceProjet WHERE idProjet = ? AND idCompetence = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProjet);
            ps.setInt(2, idCompetence);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CompetenceProjet(rs.getInt("idCompetence"), rs.getInt("idProjet"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de récupération CompetenceProjet : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Integer> rech_CP(int idProjet) {
        List<Integer> listeCompetences = new ArrayList<>();
        String sql = "SELECT idCompetence FROM CompetenceProjet WHERE idProjet = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProjet);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listeCompetences.add(rs.getInt("idCompetence"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de récupération des Compétences du projet : " + e.getMessage());
        }
        return listeCompetences;
    }

    @Override
    public List<CompetenceProjet> ListeCP() {
        List<CompetenceProjet> liste = new ArrayList<>();
        String sql = "SELECT * FROM CompetenceProjet";
        try (Connection conn = ConnectBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new CompetenceProjet(rs.getInt("idCompetence"), rs.getInt("idProjet")));
            }
        } catch (SQLException e) {
            System.err.println("Erreur récupération toutes les CompetenceProjet : " + e.getMessage());
        }
        return liste;
    }

    @Override
    public boolean mise_a_jour_CP(int ancienIdProjet, int ancienIdCompetence, CompetenceProjet nouvelleAssociation) {
        String sql = "UPDATE CompetenceProjet SET idCompetence = ?, idProjet = ? WHERE idProjet = ? AND idCompetence = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nouvelleAssociation.getCompetenceId());
            pstmt.setInt(2, nouvelleAssociation.getIdProjet());
            pstmt.setInt(3, ancienIdProjet);
            pstmt.setInt(4, ancienIdCompetence);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur mise à jour CompetenceProjet : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean suppr_CP(int idProjet, int idCompetence) {
        String sql = "DELETE FROM CompetenceProjet WHERE idProjet = ? AND idCompetence = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idProjet);
            pstmt.setInt(2, idCompetence);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur suppression CompetenceProjet : " + e.getMessage());
            return false;
        }
    }
}
