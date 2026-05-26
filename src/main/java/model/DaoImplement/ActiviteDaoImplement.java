package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Activite;
import main.java.model.dao.ActiviteDao;
import main.java.model.enums.Statut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActiviteDaoImplement implements ActiviteDao {

    @Override
    public void ajouterActivite(Activite activite) {
        String sql = "INSERT INTO activite(titre, description, ordre, duree, montant_activite, statut) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);){
            ps.setString(1, activite.getTitre());
            ps.setString(2, activite.getDescription());
            ps.setInt(3, activite.getOrdre());
            ps.setInt(4, activite.getDuree());
            ps.setInt(5, activite.getMontantActivite());
            ps.setString(6, String.valueOf(activite.getStatutActivite()));

            ps.executeUpdate();

            System.out.println("Activité ajouter avec succès ! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Activite> afficherActivite() {
        List<Activite> activites = new ArrayList<>();

        String sql = "SELECT * FROM activite";

        try (Connection connection = ConnectBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();){

            while (rs.next()){
                Activite activite = new Activite();
                activite.setId(rs.getInt("id"));
                activite.setTitre(rs.getString("titre"));
                activite.setDescription(rs.getString("description"));
                activite.setDuree(rs.getInt("duree"));
                activite.setOrdre(rs.getInt("ordre"));
                activite.setMontantActivite(rs.getInt("montant_activite"));
                activite.setStatut(Statut.valueOf(rs.getString("statut")));

                //Ajout de l'activité créer depuis la base de données dans la liste d'activités activites
                activites.add(activite);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return activites;
    }

    @Override
    public void modifierActivite(Activite activite) {
        String sql = "UPDATE activite SET titre=?, description=?, ordre=?, duree=?, montant_activite=?, statut=? WHERE id=?";
        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, activite.getTitre());
            ps.setString(2, activite.getDescription());
            ps.setInt(3, activite.getOrdre());
            ps.setInt(4, activite.getDuree());
            ps.setInt(5, activite.getMontantActivite());
            ps.setString(6, String.valueOf(activite.getStatutActivite()));
            ps.setInt(7, activite.getId());

            ps.executeUpdate();
            System.out.println("Activité modifiée avec succès ! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void marquerTerminer(int id) {
        String sql = "UPDATE activite SET statut=? WHERE id=?";

        try (Connection connection = ConnectBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, String.valueOf(Statut.TERMINER));
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Activité marquée comme terminer avec succès ! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void supprimerActivite(int id) {
        String sql = "DELETE FROM activite WHERE id=?";
        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("Activité supprimée avec succès ! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}