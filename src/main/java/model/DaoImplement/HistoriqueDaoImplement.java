package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Historique;
import main.java.model.classes.Projet;
import main.java.model.dao.HistoriqueDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDaoImplement implements HistoriqueDao {
    @Override
    public int ajouterHistorique(Historique historique) {
        String sql = "INSERT INTO historique (idClient, descriptionAction ) VALUES (?, ?)";
        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, historique.getIdClient());
            ps.setString(2, historique.getDescriptionAction());

            ps.executeUpdate();
            try(ResultSet rs= ps.getGeneratedKeys()) {
                if(rs.next()) {
                    return rs.getInt(1);
                }

            }
            System.out.println("Historique ajoutée avec succès !");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Historique> afficherHistorique() {
        String sql = "SELECT * FROM historique";
        List<Historique> historiques = new ArrayList<>();

        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                Historique historique = new Historique();
                historique.setId(rs.getInt("id"));
                historique.setDate(rs.getDate("date").toLocalDate());
                historique.setIdClient(rs.getInt("idClient"));
                historique.setDescriptionAction(rs.getString("descriptionAction"));

                historiques.add(historique);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historiques;
    }

    @Override
    public List<Historique> afficherHistoriqueClient(int idClient) {
        String sql = "SELECT titre, description, duree, budgetMin, budgetMax, date FROM client as cl INNER JOIN historique as h ON h.idClient = cl.id INNER JOIN historiqueProjet as ph ON ph.idHistorique = h.id INNER JOIN projet as p ON p.id = ph.idProjet WHERE cl.id = ?";
        List<Historique> historiques = new ArrayList<>();

        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
            ) {
            ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Historique historique = new Historique();
                Projet projet = new Projet();
                historique.setDate(rs.getDate("date").toLocalDate());
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("description"));
                projet.setDuree(rs.getInt("duree"));
                projet.setBudgetMin(rs.getInt("budgetMin"));
                projet.setBudgetMin(rs.getInt("budgetMax"));

                historiques.add(historique);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historiques;
    }

    @Override
    public void supprimerHistorique(int id) {

        String sql = "DELETE FROM historique WHERE id=?";

        try (Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}