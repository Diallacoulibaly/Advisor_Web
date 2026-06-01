package main.java.model.DaoImplement;

import main.java.model.dao.CompetenceProjetDao;
import main.java.Database.ConnectBD;
import main.java.model.classes.CompetenceProjet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetenceProjetDaoImplement implements CompetenceProjetDao {

    // Ajout
    @Override
    public boolean add_CP(CompetenceProjet cp) {
        String sql = "INSERT INTO CompetenceProjet (competenceId, projetId) VALUES (?, ?)";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cp.getCompetenceId());
            pstmt.setInt(2, cp.getIdProjet());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout CompetenceProjet : " + e.getMessage());
        }
        return false;
    }

    // Rechercher : Retourne la liste des ID de compétences associées à un projet
    @Override
    public List<Integer> rech_CP(int idProjet) {
        List<Integer> listeCompetences = new ArrayList<>();
        String sql = "SELECT competenceId FROM CompetenceProjet WHERE projetId = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idProjet);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listeCompetences.add(rs.getInt("competenceId"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de récupération des Compétences du projet : " + e.getMessage());
        }
        return listeCompetences;
    }

    // Liste complète
    @Override
    public List<CompetenceProjet> ListeCP() {
        List<CompetenceProjet> liste = new ArrayList<>();
        String sql = "SELECT * FROM CompetenceProjet";

        try (Connection conn = ConnectBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CompetenceProjet cp = new CompetenceProjet(
                        rs.getInt("competenceId"),
                        rs.getInt("projetId")
                );

                liste.add(cp);
            }
        } catch (SQLException e) {
            System.err.println("Erreur récupération toutes les CompetenceProjet : " + e.getMessage());
        }
        return liste;
    }

    // Mise à jour
    @Override
    public boolean mise_a_jour_CP(CompetenceProjet cp) {
        String sql = "UPDATE CompetenceProjet SET competenceId = ?, projetId = ? WHERE id = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cp.getCompetenceId());
            pstmt.setInt(2, cp.getIdProjet());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur mise à jour CompetenceProjet : " + e.getMessage());
            return false;
        }
    }

    // Suppression
    @Override
    public boolean suppr_CP(int id) {
        String sql = "DELETE FROM CompetenceProjet WHERE id = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Erreur suppression CompetenceProjet : " + e.getMessage());
            return false;
        }
    }

    // Vérification
    @Override
    public boolean verif_CP(int id) {
        String sql = "SELECT 1 FROM CompetenceProjet WHERE id = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }
}
