package main.java.model.DaoImplement;

import main.java.model.classes.Etape;
import main.java.model.classes.Projet;
import main.java.model.enums.StatutEtape;
import main.java.Database.ConnectBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtapeDaoImplement {

    // AJOUT
    public boolean ajout_etape(Etape etape) {
        String sql = "INSERT INTO etape (titre, description, ordre, etapeStatut, projet) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, etape.getTitre());
            stmt.setString(2, etape.getDescription());
            stmt.setInt(3, etape.getOrdre());
            stmt.setString(4, etape.getStatutEtape() != null ? etape.getStatutEtape().name() : null);
            stmt.setInt(5, etape.getProjet() != null ? etape.getProjet().getId() : 0);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Étape ajoutée avec succès !");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'étape !");
            e.printStackTrace();
        }
        return false;
    }

    // RECHERCHE
    public Optional<Etape> rech_etape(int idEtape) {
        String sql = "SELECT * FROM etape WHERE idEtape = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtape);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Etape etape = new Etape();
                etape.setIdEtape(rs.getInt("idEtape"));
                etape.setTitre(rs.getString("titre"));
                etape.setDescription(rs.getString("description"));
                etape.setOrdre(rs.getInt("ordre"));

                String statut = rs.getString("etapeStatut");
                if (statut != null) {
                    etape.setStatutEtape(StatutEtape.valueOf(statut));
                }

                Projet projet = new Projet();
                projet.setId(rs.getInt("projet"));
                etape.setProjet(projet);

                return Optional.of(etape);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche de l'étape : " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // ==================== LISTE ====================
    public List<Etape> Liste_etape() {
        String sql = "SELECT * FROM etape ORDER BY ordre ASC";
        List<Etape> etapeList = new ArrayList<>();

        try (Connection conn = ConnectBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Etape e = new Etape();
                e.setIdEtape(rs.getInt("idEtape"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setOrdre(rs.getInt("ordre"));

                String statut = rs.getString("etapeStatut");
                if (statut != null) {
                    e.setStatutEtape(StatutEtape.valueOf(statut));
                }

                Projet p = new Projet();
                p.setId(rs.getInt("projet"));
                e.setProjet(p);

                etapeList.add(e);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de la liste des étapes !");
            e.printStackTrace();
        }
        return etapeList;
    }

    // MISE À JOUR
    public boolean mise_a_jour_etape(Etape etape) {
        String sql = "UPDATE etape SET titre = ?, description = ?, ordre = ?, etapeStatut = ?, projet = ? WHERE idEtape = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etape.getTitre());
            stmt.setString(2, etape.getDescription());
            stmt.setInt(3, etape.getOrdre());
            stmt.setString(4, etape.getStatutEtape() != null ? etape.getStatutEtape().name() : null);
            stmt.setInt(5, etape.getProjet() != null ? etape.getProjet().getId() : 0);
            stmt.setInt(6, etape.getIdEtape());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Étape mise à jour avec succès !");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de l'étape !");
            e.printStackTrace();
        }
        return false;
    }

    // SUPPRESSION
    public boolean suppr_etape(int idEtape) {
        String sql = "DELETE FROM etape WHERE idEtape = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtape);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Étape supprimée avec succès !");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'étape !");
            e.printStackTrace();
        }
        return false;
    }

    // VERIFICATION
    public boolean verif_etape(int idEtape) {
        String sql = "SELECT 1 FROM etape WHERE idEtape = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtape);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Erreur lors de la vérification de l'étape : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}