package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Domaine;
import main.java.model.classes.Localite;
import main.java.model.classes.Projet;
import main.java.model.dao.ProjetDao;
import main.java.model.enums.Niveau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetDaoImpl implements ProjetDao {

    public ProjetDaoImpl() {}

    @Override
    public boolean existsByTitre(String titre) {
        String query = "SELECT COUNT(*) FROM projet WHERE titre = ?";
        try (Connection connection= ConnectBD.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, titre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification du titre : " + e.getMessage());
        }
        return false;
    }

    @Override
    public void add(Projet p) {
        String sql = "INSERT INTO Projet (titre, description, duree, niveau, budgetMin, budgetMax) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectBD.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, p.getTitre());
                ps.setString(2, p.getDescription());
                ps.setFloat(3, p.getDuree());
                ps.setString(4, p.getNiveau().name());
                ps.setDouble(5, p.getBudgetMin());
                ps.setDouble(6, p.getBudgetMax());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du projet : " + e.getMessage());
        }
    }

    @Override
    public List<Projet> getAll() {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM Projet";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Projet p = mapResultSetToProjet(rs);
                projets.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des projets : " + e.getMessage());
        }
        return projets;
    }

    @Override
    public Optional<Projet> getById(int id)  {
        String sql = "SELECT p.id AS projetId, p.titre AS titre, p.niveau AS niveau, p.budgetMin AS budgetMin, p.budgetMax AS budgetMax, p.description AS description, p.duree AS duree, d.id AS domaineId, d.domaine AS domaine, l.id AS localiteId, l.regionClient AS region FROM projet p JOIN domaine d ON d.id= p.idDomaine JOIN localite l ON l.id=p.idlocalite WHERE p.id = ?";
        try (Connection conn = ConnectBD.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) return Optional.of(mapResultSetToProjet(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du projet : " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void update(Projet p) {
        String sql = "UPDATE Projet SET titre=?, description=?, duree=?, niveau=?, budgetMin=?, budgetMax=? WHERE id=?";
        try (Connection conn = ConnectBD.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, p.getTitre());
                ps.setString(2, p.getDescription());
                ps.setFloat(3, p.getDuree());
                ps.setString(4, p.getNiveau().name());
                ps.setDouble(5, p.getBudgetMin());
                ps.setDouble(6, p.getBudgetMax());
                ps.setInt(7, p.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'update' : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Projet WHERE id = ?";
        try (Connection conn = ConnectBD.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du projet : " + e.getMessage());
        }
    }


    // pour éviter la répétition de code
    private Projet mapResultSetToProjet(ResultSet rs) throws SQLException {
        Projet p = new Projet();
        Domaine domaine= new Domaine(rs.getInt("domaineId"), rs.getString("domaine"));
        Localite localite= new Localite();
        localite.setId(rs.getInt("localiteId"));
        localite.setRegionClient(rs.getString("region"));
        p.setId(rs.getInt("projetId"));
        p.setTitre(rs.getString("titre"));
        p.setDescription(rs.getString("description"));
        p.setDuree(rs.getFloat("duree"));
        p.setNiveau(Niveau.valueOf(rs.getString("niveau")));
        p.setBudgetMin(rs.getDouble("budgetMin"));
        p.setBudgetMax(rs.getDouble("budgetMax"));
        p.setDomaine(domaine);
        p.setLocalite(localite);
        return p;
    }
}