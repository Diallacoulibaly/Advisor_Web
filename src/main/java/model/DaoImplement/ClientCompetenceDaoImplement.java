package main.java.model.DaoImplement;

package DAOimplementation;

import DAO.ClientCompetenceRepository;
import BD.ConnexionBdd;
import Models.Client;
import Models.ClientCompetence;
import Models.Competence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ClientCompetenceDaoImplement implements ClientCompetenceRepository {

    @Override
    public void add(ClientCompetence clientCompetence) {

        String sql = "INSERT INTO ClientCompetence (idClient, idCompetence) VALUES (?, ?)";

        try (Connection cnn = ConnexionBdd.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {

            stml.setInt(1, clientCompetence.getClient().getIdUtilisateur());

            stml.setInt(2, clientCompetence.getCompetence().getIdCompetence());

            stml.executeUpdate();

            System.out.println("ClientCompetence ajouté avec succès");

        } catch (SQLException e) {

            System.out.println("Erreur lors de l'ajout ClientCompetence");
        }
    }

    @Override
    public List<ClientCompetence> getAll() {

        List<Models.ClientCompetence> list = new ArrayList<>();

        String sql = "SELECT * FROM ClientCompetence";

        try (Connection cnn = ConnexionBdd.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql);
             ResultSet rs = stml.executeQuery()) {

            while (rs.next()) {

                ClientCompetence cc = new ClientCompetence();
                Client cl = new Client();
                Competence c = new Competence();
                cc.setId(rs.getInt("id"));
                cl.setIdUtilisateur(rs.getInt("idClient"));
                c.setIdCompetence(rs.getInt("idCompetence"));
                cc.setClient(cl);
                cc.setCompetence(c);
                list.add(cc);


            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void update(ClientCompetence clientCompetence) {
        String sql = "UPDATE ClientCompetence " +
                "SET idClient = ?, idCompetence = ? " +
                "WHERE id = ?";
        try (Connection cnn = ConnexionBdd.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {

            stml.setInt(1, clientCompetence.getClient().getIdUtilisateur());

            stml.setInt(2, clientCompetence.getCompetence().getIdCompetence());

            stml.setInt(3, clientCompetence.getId());

            stml.executeUpdate();

            System.out.println("Modification réussie");

        } catch (SQLException e) {

            System.out.println("Erreur lors de la modification");
        }
    }

    @Override
    public List<Integer> getSkillsByClient(int idClient) throws SQLException {
        List<Integer> skillIds = new ArrayList<>();
        String sql = "SELECT idCompetence FROM ClientCompetence WHERE idClient = ?";

        try (Connection conn = ConnexionBdd.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idClient);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    skillIds.add(rs.getInt("idCompetence"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
            throw e;
        }
        return skillIds;
    }

    @Override
    public void delete(int id) {

        String sql = "DELETE FROM ClientCompetence WHERE id = ?";

        try (Connection cnn = ConnexionBdd.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {

            stml.setInt(1, id);

            stml.executeUpdate();

            System.out.println("Suppression réussie");

        } catch (SQLException e) {

            System.out.println("Erreur lors de la suppression");
        }
    }
}

