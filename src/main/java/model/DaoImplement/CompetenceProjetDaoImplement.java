//package main.java.model.DaoImplement;
//import main.java.model.dao.CompetenceProjetDao;
//import main.java.Database.ConnectBD;
//import main.java.model.classes.CompetenceProjet;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class CompetenceProjetDaoImplement implements CompetenceProjetDao{
//
//    // Ajout
//    @Override
//    public boolean ajout(CompetenceProjet cp) {
//        String sql = "INSERT INTO CompetenceProjet (competenceId, projetId) VALUES (?, ?)";
//
//        try (Connection conn = ConnectBD.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, cp.getCompetenceId());
//            pstmt.setInt(2, cp.getIdProjet());
//
//            int rows = pstmt.executeUpdate();
//            return rows > 0;
//
//        } catch (SQLException e) {
//            System.err.println("Erreur lors de l'ajout CompetenceProjet : " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Rechercher
//    @Override
//    public Optional<CompetenceProjet> rech_competence(int id) {
//        String sql = "SELECT * FROM CompetenceProjet WHERE id = ?";
//
//        try (Connection conn = ConnectBD.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                CompetenceProjet cp = new CompetenceProjet(
//                        rs.getInt("competenceId"),
//                        rs.getInt("projetId")
//                );
//                return Optional.of(cp);
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur recherche CompetenceProjet : " + e.getMessage());
//        }
//        return Optional.empty();
//    }
//
//    // Liste
//    @Override
//    public List<CompetenceProjet> tous_les_competences() {
//        List<CompetenceProjet> liste = new ArrayList<>();
//        String sql = "SELECT * FROM CompetenceProjet";
//
//        try (Connection conn = ConnectBD.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql)) {
//
//            while (rs.next()) {
//                CompetenceProjet cp = new CompetenceProjet(
//                        rs.getInt("competenceId"),
//                        rs.getInt("projetId")
//                );
//                liste.add(cp);
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur récupération toutes les CompetenceProjet : " + e.getMessage());
//        }
//        return liste;
//    }
//
//    // Mise à jour
//    @Override
//    public boolean mise_a_jour(CompetenceProjet cp) {
//        String sql = "UPDATE CompetenceProjet SET competenceId = ?, projetId = ? WHERE id = ?";
//
//        try (Connection conn = ConnectBD.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, cp.getCompetenceId());
//            pstmt.setInt(2, cp.getIdProjet());
//            // pstmt.setInt(3, id);   // À ajuster si tu as un id dans la table
//
//            int rows = pstmt.executeUpdate();
//            return rows > 0;
//
//        } catch (SQLException e) {
//            System.err.println("Erreur mise à jour CompetenceProjet : " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Suppression
//    @Override
//    public boolean supprimer(int id) {
//        String sql = "DELETE FROM CompetenceProjet WHERE id = ?";
//
//        try (Connection conn = ConnectBD.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, id);
//            int rows = pstmt.executeUpdate();
//            return rows > 0;
//
//        } catch (SQLException e) {
//            System.err.println("Erreur suppression CompetenceProjet : " + e.getMessage());
//            return false;
//        }
//    }
//
//
//    @Override
//    public List<Integer> getSkillsByProjet(int idProjet) {
//        List<Integer> skillIds = new ArrayList<>();
//        String sql = "SELECT idProjet FROM ProjetCompetence WHERE idProjet = ?";
//
//        try (Connection conn = ConnectBD.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setInt(1, idProjet);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    skillIds.add(rs.getInt("idCompetence"));
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erreur de récupération des Compétences du projet : " + e.getMessage());
//        }
//        return skillIds;
//    }
//
//
//    // Verification
//    @Override
//    public boolean verifier_existance(int id) {
//        String sql = "SELECT 1 FROM CompetenceProjet WHERE id = ?";
//        try (Connection conn = ConnectBD.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            return false;
//        }
//    }
//}
