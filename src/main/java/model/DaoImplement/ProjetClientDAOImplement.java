package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.*;
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
    public boolean save(ProjetClient projetClient) {
        String sql = """
                INSERT INTO projetClient
                (idClient, idProjet, statut)
                VALUES (?, ?, ?)
                """;
        try (Connection connection= ConnectBD.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)){


                    stmt.setInt(1, projetClient.getClient().getIdUtilisateur());

                    stmt.setInt(2, projetClient.getProjet().getId());

                    stmt.setString(3, StatutProjet.ENCOURS.name());

                    int row=stmt.executeUpdate();

                    return row>0;
            }

        catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur lors de l'ajout du projet", e);
        }
    }

    @Override
    public boolean hasProjetEnCours(int idClient) {

        String sql = """
            SELECT 1
            FROM projetClient
            WHERE idClient = ?
            AND statut = ?
            LIMIT 1
            """;

        try (Connection connection = ConnectBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idClient);
            ps.setString(2, StatutProjet.ENCOURS.name());

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erreur lors de la vérification du projet en cours",
                    e);
        }
    }

    @Override
    public void changerStatut(int id, StatutProjet statutProjet) {

        String sql = """
                UPDATE projetClient
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
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.description AS description, p.titre AS titre,p.budgetMax AS budgetPrevu, p.duree AS duree, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region, d.domaine AS domaine, d.id AS domaineId
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                JOIN domaine d ON d.id=p.idDomaine
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
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.titre AS titre, p.description AS description, p.budgetMax AS budgetPrevu, p.duree AS duree, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region, d.domaine AS domaine, d.id AS domaineId
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                JOIN domaine d ON d.id=p.idDomaine
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
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.titre AS titre,p.budgetMax AS budgetPrevu, p.duree AS duree, p.description AS description, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region, d.domaine AS domaine, d.id AS domaineId
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                JOIN domaine d ON d.id=p.idDomaine
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
        Domaine domaine= new Domaine(rs.getInt("domaineId"), rs.getString("domaine"));
        projet.setId(rs.getInt("idProjet"));
        projet.setTitre(rs.getString("titre"));
        projet.setDescription(rs.getString("description"));
        projet.setBudgetMax(rs.getInt("budgetPrevu"));
        projet.setDuree(rs.getFloat("duree"));
        projet.setLocalite(localite);
        projet.setDomaine(domaine);
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

    @Override
    public Optional<ProjetClient> getProjetEnCours(int idClient) {
        String sql = """
                SELECT u.id AS idUser, u.nom AS nom, u.prenom AS prenom, u.email AS email, c.budgetApporte AS budget, c.niveau AS niveau, p.id AS idProjet, p.titre AS titre, p.budgetMax AS budgetPrevu, p.description AS description,  p.duree AS duree, pc.id AS idProjetClient, pc.debut AS debut, pc.statut AS statut, pc.satisfaction AS satisfaction, l.id AS idLocalite, l.regionClient AS region, d.domaine AS domaine, d.id AS domaineId
                FROM ProjetClient pc 
                JOIN client c ON c.id= pc.idClient
                JOIN projet p ON p.id=pc.idProjet
                JOIN utilisateur u ON u.id=c.id
                JOIN localite l ON c.idLocalite=l.id
                JOIN domaine d ON d.id=p.idDomaine
                WHERE pc.idClient=? AND pc.statut="ENCOURS"
                """;



        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {

            stmt.setInt(1, idClient);
            try (ResultSet rs = stmt.executeQuery();){
                if (rs.next()) {
                    return Optional.of(mapProjetClient(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erreur récupération projets", e);
        }

        return Optional.empty();
    }

    @Override
    public double getProjetDepenseEnCours(int idClient) {
        String sql = """
                SELECT SUM(montant) As totalDepenceparprojet
                FROM ProjetClient pc 
                JOIN Projet as p on p.id=pc.idProjet
                JOIN Client as cl on cl.id=pc.idClient
                JOIN depense as d on d.idClient=cl.id
                JOIN Utilisateur as u on u.id=cl.id
                WHERE pc.idClient=? AND pc.statut="ENCOURS"
                """;


        try(Connection conn = ConnectBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idClient);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getDouble("totalDepenceparprojet");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;



    }


}
