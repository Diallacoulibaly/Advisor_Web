package main.java.model.DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.ConnectBD;
import model.classes.Localite;
import model.dao.LocaliteDao;

public class LocaliteDaoImplement implements LocaliteDao{
    @Override
        public void add(Localite localite) {
            String sql = "INSERT INTO localite(regionClient) VALUES(?)";
            try (Connection connection = ConnectBD.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, localite.getRegionClient());
                ps.executeUpdate();
                System.out.println("Localité ajoutée avec succès !!!!!!");

            } catch (SQLException e) {
                System.out.println("Erreur lors de l'enregistrement" + e.getMessage());
            }

        }


        @Override
        public void delete(int id) {
            String sql = "DELETE FROM localite WHERE(id=?)";
            try (Connection connection = ConnectBD.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Localité supprimée avec succès !!!!!!");


            } catch (SQLException e) {
                System.out.println("Erreur lors de la suppression" + e.getMessage());
            }
        }

        @Override
        public List<Localite> getAll() {
            String sql = "SELECT * FROM localite";
            List<Localite> localites = new ArrayList<>();
            try (Connection connection = ConnectBD.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {

                    while (rs.next()) {
                        Localite localite = new Localite();
                        localite.setId(rs.getInt("id"));
                        localite.setRegionClient(rs.getString("regionClient"));
                        localites.add(localite);
                    }

                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la recuperation de la liste des localités");
            }
            return localites;
        }

        @Override
        public Localite getById(int id) {
            String sql = "SELECT * FROM localite WHERE(id=?)";
            try (Connection connection = ConnectBD.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Localite localite = new Localite(
                                rs.getInt("id"),
                                rs.getString("regionClient"));
                        return localite;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la recuperation du client" + e.getMessage());
            }
            return null;
        }

        @Override
        public void update(int id, String regionClient) {
            String sql = "UPDATE localite SET regionClient=? WHERE id=?";
            try (Connection connection = ConnectBD.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, regionClient);
                ps.setInt(2, id);
                ps.executeUpdate();
                System.out.println("Localité modifié avec succès !!!!!");

            } catch (SQLException e) {
                System.out.println("Erreur lors de la modification de la localité " + e.getMessage());
            }
        }
}
