package main.java.model.DaoImplement;

import main.java.model.classes.Etape;
import main.java.model.classes.Projet;
import main.java.model.dao.EtapeDao;
import main.java.model.enums.StatutEtape;
import main.java.Database.ConnectBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EtapeDaoImplement implements EtapeDao {

    // AJOUT
    public boolean ajout_etape(Etape etape) {
        String sql = "INSERT INTO etape (titre, description, ordre, statut, idProjet) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, etape.getTitre());
            stmt.setString(2, etape.getDescription());
            stmt.setInt(3, etape.getOrdre());
            stmt.setString(4, etape.getStatutEtape() != null ? etape.getStatutEtape().name() : null);
            stmt.setInt(5, etape.getProjet() != null ? etape.getProjet().getId() : 0);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Etape ajoutee avec succes !");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l ajout de l etape !");
            e.printStackTrace();
        }
        return false;
    }

    // RECHERCHE
    public Optional<Etape> rech_etape(int idEtape) {
        String sql = "SELECT * FROM etape WHERE id = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtape);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Etape etape = new Etape();
                etape.setIdEtape(rs.getInt("id"));
                etape.setTitre(rs.getString("titre"));
                rs.getString("description"); // maintient la lecture mais préférez l'affecter s'il manque:
                etape.setDescription(rs.getString("description"));
                etape.setOrdre(rs.getInt("ordre"));

                String statut = rs.getString("statut");
                if (statut != null) {
                    etape.setStatutEtape(StatutEtape.valueOf(statut));
                }

                Projet projet = new Projet();
                projet.setId(rs.getInt("idProjet"));
                etape.setProjet(projet);

                return Optional.of(etape);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche de l'étape : " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //  LISTE
    public List<Etape> Liste_etape() {
        String sql = "SELECT id, idProjet, titre, description, ordre, statut FROM etape ORDER BY ordre ASC";
        List<Etape> etapeList = new ArrayList<>();

        try (Connection conn = ConnectBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Etape e = new Etape();
                e.setIdEtape(rs.getInt("id"));
                e.setTitre(rs.getString("titre"));
                e.setDescription(rs.getString("description"));
                e.setOrdre(rs.getInt("ordre"));

                // Gestion sécurisée du Statut Enum
                String statutStr = rs.getString("statut");
                if (statutStr != null) {
                    try {
                        e.setStatutEtape(StatutEtape.valueOf(statutStr));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Attention : Le statut '" + statutStr + "' n'existe pas dans l'Enum StatutEtape.");
                    }
                }

                Projet p = new Projet();
                p.setId(rs.getInt("idProjet"));
                e.setProjet(p);

                etapeList.add(e);
            }
        } catch (Exception e) {
            // CORRECTION CRITIQUE : Forcer l'affichage de l'erreur dans la console IntelliJ
            System.out.println("Erreur critique lors de la récupération de la liste des étapes :");
            e.printStackTrace();
        }
        return etapeList;
    }


    // MISE À JOUR
    public boolean mise_a_jour_etape(Etape etape) {
        String sql = "UPDATE etape SET titre = ?, description = ?, ordre = ?, statut = ?, idProjet = ? WHERE id = ?";

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
                System.out.println("Etape mise a jour avec succes !");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise a jour de l etape !");
            e.printStackTrace();
        }
        return false;
    }

    // SUPPRESSION
    public boolean suppr_etape(int idEtape) {
        String sql = "DELETE FROM etape WHERE id = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtape);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Etape supprimee avec succes !");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l etape !");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Etape> getByProjetId(int idProjet) {
        String sql= "SELECT * FROM etape WHERE idProjet = ? ORDER BY ordre ASC";
        List<Etape> etapeList = new ArrayList<>();
        try(Connection conn= ConnectBD.getConnection(); PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setInt(1, idProjet);
            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {

                    StatutEtape statut = StatutEtape.valueOf(rs.getString("statut"));
                    Etape etape = new Etape();
                    etape.setIdEtape(rs.getInt("id"));
                    etape.setTitre(rs.getString("titre"));
                    etape.setDescription(rs.getString("description"));
                    etape.setOrdre(rs.getInt("ordre"));
                    etape.setStatutEtape(statut);
                    etapeList.add(etape);
                }
            }
        } catch (SQLException e){
            e.printStackTrace(System.out);
        }
        return etapeList;
    }

    @Override
    public int count_etape(int idProjet) {
        String sql = "SELECT COUNT(*) FROM etape WHERE idProjet = ?";
        try(Connection connection= ConnectBD.getConnection(); PreparedStatement ps= connection.prepareStatement(sql)) {
           ps.setInt(1, idProjet);
           try (ResultSet rs = ps.executeQuery()){
               if (rs.next()) {
                   return rs.getInt(1);
               }
           }
        } catch (SQLException e){
            e.printStackTrace(System.out);
        }
        return 0;
    }
}
