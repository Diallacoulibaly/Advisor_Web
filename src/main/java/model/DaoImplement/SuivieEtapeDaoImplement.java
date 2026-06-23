package main.java.model.DaoImplement;

import main.java.model.classes.Etape;
import main.java.model.classes.SuivieEtape;
import main.java.model.dao.SuivieEtapeDao;
import main.java.model.enums.StatutEtape;
import main.java.Database.ConnectBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SuivieEtapeDaoImplement implements SuivieEtapeDao {

    @Override
    public void changerStatut(int id, StatutEtape statutEtape) {
        String sql = "UPDATE suivieEtape SET statut=? WHERE id=?";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {
            stml.setString(1, statutEtape.name());
            stml.setInt(2, id);
            stml.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajout(SuivieEtape suivieEtape) {
        String sql = "INSERT INTO suivieEtape (idEtape, idClient, statut) VALUES (?, ?, ?)";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {
            stml.setInt(1, suivieEtape.getEtape().getIdEtape());
            stml.setInt(2, suivieEtape.getClient().getIdUtilisateur());
            stml.setString(3, suivieEtape.getStatutEtape().name());
            stml.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SuivieEtape> trouverParProjetEtClient(int idProjet, int idClient) {
        List<SuivieEtape> liste = new ArrayList<>();

        // Requête alignée sur vos structures réelles :
        // s.idEtape (suivieEtape) se lie à e.id (etape)
        String sql = "SELECT s.statut, e.id AS id_de_l_etape, e.titre, e.description, e.ordre " +
                "FROM suivieEtape s " +
                "JOIN etape e ON s.idEtape = e.id " +
                "WHERE e.idProjet = ? AND s.idClient = ? " +
                "ORDER BY e.ordre ASC";

        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {

            stml.setInt(1, idProjet);
            stml.setInt(2, idClient);

            try (ResultSet rs = stml.executeQuery()) {
                while (rs.next()) {
                    SuivieEtape suivi = new SuivieEtape();

                    // Récupération du statut ENUM
                    String statutStr = rs.getString("statut");
                    if (statutStr != null) {
                        suivi.setStatutEtape(StatutEtape.valueOf(statutStr));
                    }

                    // Reconstruction de l'objet Etape lié
                    Etape etape = new Etape();
                    // On utilise l'alias 'id_de_l_etape' pour récupérer la valeur de e.id
                    etape.setIdEtape(rs.getInt("id_de_l_etape"));
                    etape.setTitre(rs.getString("titre"));
                    etape.setDescription(rs.getString("description"));
                    etape.setOrdre(rs.getInt("ordre"));

                    suivi.setEtape(etape);
                    liste.add(suivi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }


    @Override
    public void validerEtapeEtOuvrirSuivante(int idEtape, int idClient) {
        String sqlActuelle = "UPDATE suivieEtape SET statut = 'TERMINE' WHERE idEtape = ? AND idClient = ?";

        String sqlSuivante = "UPDATE suivieEtape s " +
                "JOIN etape e_suivante ON s.idEtape = e_suivante.id " +
                "SET s.statut = 'ENCOURS' " +
                "WHERE s.idClient = ? AND s.statut = 'AFAIRE' " +
                "AND e_suivante.idProjet = (SELECT idProjet FROM etape WHERE id = ?) " +
                "AND e_suivante.ordre = (SELECT ordre + 1 FROM etape WHERE id = ?)";

        try (Connection cnn = ConnectBD.getConnection()) {
            cnn.setAutoCommit(false);

            try (PreparedStatement st1 = cnn.prepareStatement(sqlActuelle);
                 PreparedStatement st2 = cnn.prepareStatement(sqlSuivante)) {

                st1.setInt(1, idEtape);
                st1.setInt(2, idClient);
                st1.executeUpdate();

                st2.setInt(1, idClient);
                st2.setInt(2, idEtape);
                st2.setInt(3, idEtape);
                st2.executeUpdate();

                cnn.commit();
            } catch (Exception e) {
                cnn.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
