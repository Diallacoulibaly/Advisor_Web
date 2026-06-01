package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Competence;
import main.java.model.dao.CompetenceDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetenceDaoImplement implements CompetenceDao {

    @Override
    public void ajouterCompetence(Competence competence) {
        String sql = "INSERT INTO competence(nom) VALUES(?)";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {
            stmt.setString(1, competence.getNom());
            stmt.executeUpdate();
            System.out.println("Compétence ajoutée avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de la compétence");
            e.printStackTrace();
        }
    }

    @Override
    public List<Competence> afficherCompetences() {
        List<Competence> liste = new ArrayList<>();
        String sql = "SELECT * FROM competence";
        try (Connection cnn = ConnectBD.getConnection();
             Statement stmt = cnn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Competence c = new Competence(
                        rs.getInt("id"),
                        rs.getString("nom")
                );
                liste.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public Competence getById(int id) {
        String sql = "SELECT * FROM competence WHERE id = ?";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Competence(
                            rs.getInt("id"),
                            rs.getString("nom")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la compétence : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void modifierCompetence(Competence competence) {
        String sql = "UPDATE competence SET nom = ? WHERE id = ?";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {
            stmt.setString(1, competence.getNom());
            stmt.setInt(2, competence.getId());
            stmt.executeUpdate();
            System.out.println("Compétence modifiée avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification de la compétence");
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerCompetence(int id) {
        String sql = "DELETE FROM competence WHERE id = ?";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stmt = cnn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Compétence supprimée avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de la compétence");
            e.printStackTrace();
        }
    }
}
