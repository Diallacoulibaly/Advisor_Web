package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Historique;
import main.java.model.dao.HistoriqueDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueDaoImplement implements HistoriqueDao {
    @Override
    public void ajouterHistorique(Historique historique) {
        String sql = "INSERT INTO historique (date, descriptionAction ) VALUES (?, ?)";
        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(historique.getDate()));
            //ps.setInt(2, historique.getBudgetApporte());
            ps.setString(2, historique.getDescriptionAction());

            ps.executeUpdate();
            System.out.println("Historique ajoutée avec succès !");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                //historique.setBudgetApporte(rs.getInt("budgetApporte"));
                historique.setDescriptionAction(rs.getString("descriptionAction"));

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