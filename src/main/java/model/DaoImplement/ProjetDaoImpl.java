package main.java.model.DaoImplement;




import main.java.Database.ConnectBD;
import main.java.model.classes.Projet;
import main.java.model.dao.ProjetDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetDaoImpl implements ProjetDao {

    public ProjetDaoImpl() {}

    @Override
    public void add(Projet projet) {
        String query = "INSERT INTO projets (titre, description, duree, budgetMin, budgetMax) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection connection= ConnectBD.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, projet.getTitre());
            statement.setString(2, projet.getDescription());
            statement.setFloat(3, projet.getDuree());
            statement.setDouble(4, projet.getBudgetMin());
            statement.setDouble(5, projet.getBudgetMax());
            //statement.setString(6, projet.getProjetStatut().name());

            statement.executeUpdate();

            // Récupération de l'ID auto-généré par la base de données
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    projet.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du projet : " + e.getMessage());
        }
    }

    @Override
    public Optional<Projet> getById(int id) {
        String query = "SELECT * FROM projets WHERE id = ?";
        try (Connection connection= ConnectBD.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Projet projet = mapResultSetToProjet(resultSet);
                    return Optional.of(projet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération par ID : " + e.getMessage());
        }
        return Optional.empty();
    }


    @Override
    public List<Projet> getAll() {
        List<Projet> projets = new ArrayList<>();
        String query = "SELECT * FROM projet";
        try (Connection connection= ConnectBD.getConnection();Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                projets.add(mapResultSetToProjet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de tous les projets : " + e.getMessage());
        }
        return projets;
    }

    @Override
    public void update(int id) {
        // Note : En JDBC pur, pour faire un update avec uniquement l'ID, on applique une logique fixe
        // Idéalement, il faudrait passer l'objet Projet entier. Exemple ici de passage au statut TERMINE par l'ID :
        String query = "UPDATE projets SET statut = ? WHERE id = ?";
        try (Connection connection= ConnectBD.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setString(1, StatutProjet.Termine.name());
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Projet ID " + id + " mis à jour (Statut: TERMINE).");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du projet : " + e.getMessage());
        }
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM projets WHERE id = ?";
        try (Connection connection= ConnectBD.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate(); // Retourne le nombre de lignes supprimées (1 si succès, 0 si aucun)
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du projet : " + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean existsByTitre(String titre) {
        String query = "SELECT COUNT(*) FROM projets WHERE titre = ?";
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
    public List<Projet> getProjetsByClient(int clientId) {
        List<Projet> projets = new ArrayList<>();
        // Jointure SQL utilisant la table d'association projets_clients (ProjetClient)
        String query = "SELECT p.* FROM projets p " +
                "JOIN projets_clients pc ON p.id = pc.projet_id " +
                "WHERE pc.client_id = ?";
        try (Connection connection= ConnectBD.getConnection();PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    projets.add(mapResultSetToProjet(resultSet));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des projets du client : " + e.getMessage());
        }
        return projets;
    }

    // Méthode utilitaire privée pour transformer une ligne de la base de données en objet Java Projet
    private Projet mapResultSetToProjet(ResultSet resultSet) throws SQLException {
        Projet projet = new Projet();
        projet.setId(resultSet.getInt("id"));
        projet.setTitre(resultSet.getString("titre"));
        projet.setDescription(resultSet.getString("description"));
        projet.setDuree(resultSet.getFloat("duree"));
        projet.setBudgetMin(resultSet.getDouble("budgetMin"));
        projet.setBudgetMax(resultSet.getDouble("budgetMax"));

        // Conversion de la chaîne de caractères SQL en Enum Java
        //String statutStr = resultSet.getString("statut");
        //if (statutStr != null) {
        //projet.setProjetStatut(StatutProjet.valueOf(statutStr));
        //}

        return projet;
    }
}