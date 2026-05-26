package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Client;
import main.java.model.classes.Localite;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.ClientDAO;
import main.java.model.enums.Niveau;
import main.java.model.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImplement implements ClientDAO {

    @Override
    public boolean add(Client client) {
        try(Connection conn= ConnectBD.getConnection()) {
            conn.setAutoCommit(false);
            String insert= "INSERT INTO utilisateur (nom, prenom, email, telephone, motDePasse, role) VALUES (?, ?, ?, ?, ?, ?)";
            try(PreparedStatement stmt= conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, client.getNom());
                stmt.setString(2, client.getPrenom());
                stmt.setString(3, client.getEmail());
                stmt.setString(4, client.getTelephone());
                stmt.setString(5, client.getMotDePasse());
                stmt.setString(6, Role.CLIENT.name());
                int affected= stmt.executeUpdate();
                // System.out.println("Utilisateur enregistré !!!!");
                if(affected==0){
                    throw new SQLException("Enregistrement echoué!!");
                }
                int idUSer;
                try(ResultSet rs= stmt.getGeneratedKeys()){
                    if(!rs.next()){
                        throw new SQLException("Aucun ID retrouvé!!");
                    }
                    idUSer= rs.getInt(1);
                    String insert2= "INSERT INTO client(id) VALUES (?)";
                    try(PreparedStatement ps= conn.prepareStatement(insert2)){
                        ps.setInt(1, idUSer);
//                        ps.setString(2, client.getNiveau().name());
//                        ps.setInt(3, client.getLocalite().getId());
//                        ps.setInt(4, client.getBudgetApporte());
                        int row= ps.executeUpdate();
                        conn.commit();
                        return row>0;
                    }
                }
            }
            catch (SQLException e) {
                conn.rollback(); //Permet de tout annuler
                e.printStackTrace(System.out);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean addInfoClient(int clientID, Niveau niveau, Localite localite, int budget) {
        String sql= "UPDATE client set niveau= ?, idLocalite= ?, budgetApporte= ? WHERE id = ?";
        try(Connection connection= ConnectBD.getConnection();
            PreparedStatement ps= connection.prepareStatement(sql))    {
            ps.setString(1, niveau.name());
            ps.setInt(2, localite.getId());
            ps.setInt(3, budget);
            ps.setInt(4, clientID);
            int affected= ps.executeUpdate();
            return affected>0;
        }
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean update(int id, String nom, String prenom, String telephone, Niveau niveau, int idlocalite, int budget) {
        String updateUser = "UPDATE utilisateur SET nom=?, prenom=?, telephone=? WHERE id=?";

        String updateClient = "UPDATE client SET niveau=?, idlocalite=?, budgetApporte= ? WHERE id=?";
        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateUser);
                PreparedStatement ps = conn.prepareStatement(updateClient)
        ) {
            conn.setAutoCommit(false);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, telephone);
            stmt.setInt(4, id);

            int ligne = stmt.executeUpdate();

            if (ligne == 0) {
                conn.rollback();
                throw  new SQLException("Aucun client retrouvé avec ce ID");
            }
            ps.setString(1, niveau.name());
            ps.setInt(2, idlocalite);

            ps.setInt(3, budget);
            ps.setInt(4, id);

            int row= ps.executeUpdate();
            conn.commit();
            return row>0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public List<Client> getAll() {
        String selectAll= "SELECT u.id AS user_id, u.nom, u.prenom, u.telephone, u.role AS role, u.email, c.niveau, c.budgetApporte AS budget, l.id AS id_localite, l.regionClient AS region FROM utilisateur u JOIN client c ON u.id= c.id JOIN localite l ON l.id=c.idlocalite";
        List<Client> clients = new ArrayList<>();
        try(Connection conn= ConnectBD.getConnection();
            PreparedStatement stmt= conn.prepareStatement(selectAll)) {
            try (ResultSet rs= stmt.executeQuery()) {

                while (rs.next()) {
                    Localite localite= new Localite();
                    localite.setId(rs.getInt("id_localite"));
                    localite.setRegionClient(rs.getString("region"));
                    Client client = new Client();
                    client.setIdUtilisateur((rs.getInt("user_id")));
                    client.setNom(rs.getString("nom"));
                    client.setPrenom(rs.getString("prenom"));
                    client.setEmail(rs.getString("email"));
                    client.setBudgetApporte(rs.getInt("budget"));
                    client.setTelephone(rs.getString("telephone"));
//                        client.setMotDePasse(rs.getString("mot_de_passe"));
                    client.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
                    client.setNiveau(Niveau.valueOf(rs.getString("niveau").toUpperCase()));

                    client.setLocalite(localite);
                    clients.add(client);
                }

            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation des clients !!!!!" +e.getMessage());

        }
        return clients;

    }

    @Override
    public Optional<Client> getById(int id) {
        String selectById= "SELECT u.id AS user_id, u.nom, u.prenom, u.telephone,  u.role AS role, u.email, c.niveau, c.budgetApporte AS budget, l.id AS id_localite, l.regionClient AS region FROM utilisateur u JOIN client c ON u.id= c.id JOIN localite l ON l.id=c.idlocalite WHERE u.id = ?";
        try  (Connection conn= ConnectBD.getConnection();
              PreparedStatement stmt= conn.prepareStatement(selectById)) {
            stmt.setInt(1, id);
            try (ResultSet rs= stmt.executeQuery()) {

                if (rs.next()) {
                    Localite localite= new Localite();
                    localite.setId(rs.getInt("id_localite"));
                    localite.setRegionClient(rs.getString("region"));
                    Client client = new Client();
                    client.setIdUtilisateur((rs.getInt("user_id")));
                    client.setNom(rs.getString("nom"));
                    client.setPrenom(rs.getString("prenom"));
                    client.setEmail(rs.getString("email"));
                    client.setTelephone(rs.getString("telephone"));
                    client.setBudgetApporte(rs.getInt("budget"));
//                        client.setMotDePasse(rs.getString("mot_de_passe"));
                    client.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
                    client.setNiveau(Niveau.valueOf(rs.getString("niveau").toUpperCase()));

                    client.setLocalite(localite);

                    return Optional.of(client);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation du client !!!!!" +e.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public boolean delete(int id) {
        String delete= "DELETE FROM utilisateur WHERE id = ?";
        try(Connection conn= ConnectBD.getConnection();
            PreparedStatement stmt= conn.prepareStatement(delete)) {
            stmt.setInt(1, id);
            int ligne= stmt.executeUpdate();
            return ligne>0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        String query = "SELECT COUNT(*) FROM utilisateur WHERE email = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return false;

    }

}
