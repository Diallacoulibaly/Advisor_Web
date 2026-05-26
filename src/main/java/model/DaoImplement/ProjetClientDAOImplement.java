package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Client;
import main.java.model.classes.Localite;
import main.java.model.classes.Projet;
import main.java.model.classes.ProjetClient;
import main.java.model.dao.ProjetClientDAO;
import main.java.model.enums.Niveau;
import main.java.model.enums.Satifaction;
import main.java.model.enums.StatutProjet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetClientDAOImplement implements ProjetClientDAO {
    @Override
    public void save(ProjetClient projetClient) {

        String sql = """
                INSERT INTO ProjetClient
                (idClient, idProjet, statut, satisfaction)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, projetClient.getClient().getIdUtilisateur());

            stmt.setInt(2, projetClient.getProjet().getId());

            stmt.setString(3, projetClient.getStatut().name());

            stmt.setString(4, projetClient.getSatisfaction().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur lors de l'ajout du projet", e);
        }
    }

    @Override
    public void changerStatut(int id, StatutProjet statutProjet) {

        String sql = """
                UPDATE ProjetClient
                SET
                statut = ?,
                WHERE id = ?
                """;

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, statutProjet.name());
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur modification projet", e);
        }
    }

//    @Override
//    public void delete(int id) {
//
//        String sql = "DELETE FROM ProjetClient WHERE id = ?";
//
//        try (
//                Connection conn = ConnexionBdd.getConnection();
//                PreparedStatement stmt = conn.prepareStatement(sql)
//        ) {
//
//            stmt.setInt(1, id);
//
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(
//                    "Erreur suppression projet", e);
//        }
//    }

    @Override
    public Optional<ProjetClient> getById(int id) {

        String sql = """
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.titre AS titre, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                WHERE pc.id=?
                """;

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {

                if(rs.next()) {
                    return Optional.of(mapProjetClient(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur récupération projet", e);
        }

        return Optional.empty();
    }

    @Override
    public List<ProjetClient> getAll() {

        String sql = """
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.titre AS titre, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                """;

        List<ProjetClient> projets = new ArrayList<>();

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                projets.add(mapProjetClient(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur récupération projets", e);
        }

        return projets;
    }

    @Override
    public List<ProjetClient> getByClient(int idClient) {

        String sql = """
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.titre AS titre, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                WHERE pc.idClient=?
                """;

        List<ProjetClient> projets = new ArrayList<>();

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery();){
                while (rs.next()) {
                    projets.add(mapProjetClient(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur récupération projets", e);
        }

        return projets;
    }

    private ProjetClient mapProjetClient(ResultSet rs) throws SQLException {

        ProjetClient projetClient = new ProjetClient();

        Client client = new Client();
        Localite localite = new Localite();
        client.setIdUtilisateur(rs.getInt("idUser"));
        client.setNom(rs.getString("nom"));

        client.setPrenom(rs.getString("prenom"));
        //System.out.println("prenom "+ client.getPrenom());
        client.setEmail(rs.getString("email"));
        client.setBudgetApporte(rs.getInt("budget"));
        client.setNiveau(Niveau.valueOf(rs.getString("niveau")));
        localite.setId(rs.getInt("idLocalite"));
        localite.setRegionClient(rs.getString("region"));
        client.setLocalite(localite);
        Projet projet = new Projet();

        projet.setId(rs.getInt("idProjet"));
        projet.setTitre(rs.getString("titre"));

        projetClient.setId(rs.getInt("idProjetClient"));

        Timestamp debut = rs.getTimestamp("debut");
        if (debut != null) {
            projetClient.setDebut(debut.toLocalDateTime());
        }
        projetClient.setStatut(StatutProjet.valueOf(rs.getString("statut").toUpperCase()));
        projetClient.setSatisfaction(Satifaction.valueOf(rs.getString("satisfaction").toUpperCase()));


        projetClient.setProjet(projet);
        projetClient.setClient(client);

        return projetClient;
    }


}
