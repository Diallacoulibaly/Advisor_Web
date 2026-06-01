/*package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Utilisateur;
import main.java.model.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilisateurDaoImplement implements model.dao.UtilisateurDao {

    @Override
    public void add(Utilisateur utilisateur) {

        String sql = """
                INSERT INTO utilisateur
                (nom, prenom, email, motDePasse, telephone, role)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMotDePasse());
            stmt.setString(5, utilisateur.getTelephone());
            stmt.setString(6, utilisateur.getRole().name());

            stmt.executeUpdate();

            System.out.println("Utilisateur ajouté avec succès.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // RÉCUPÉRER PAR ID
    // =========================
    @Override
    public Optional<Utilisateur> getById(int id) {

        String sql = "SELECT * FROM utilisateur WHERE id = ?";

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Utilisateur utilisateur = new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("telephone"),
                        Role.valueOf(rs.getString("role"))
                );

                return Optional.of(utilisateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // =========================
    // RÉCUPÉRER PAR EMAIL
    // =========================
    @Override
    public Optional<Utilisateur> getByEmail(String email) {

        String sql = "SELECT * FROM utilisateur WHERE email = ?";

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Utilisateur utilisateur = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("telephone"),
                        Role.valueOf(rs.getString("role"))
                );

                return Optional.of(utilisateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    // =========================
    // RÉCUPÉRER TOUS
    // =========================
    @Override
    public List<Utilisateur> getAll() {

        List<Utilisateur> utilisateurs = new ArrayList<>();

        String sql = "SELECT * FROM utilisateur";

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Utilisateur utilisateur = new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("telephone"),
                        Role.valueOf(rs.getString("role"))
                );

                utilisateurs.add(utilisateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }

    // =========================
    // RÉCUPÉRER PAR RÔLE
    // =========================
    @Override
    public List<Utilisateur> getByRole(Role role) {

        List<Utilisateur> utilisateurs = new ArrayList<>();

        String sql = "SELECT * FROM utilisateur WHERE role = ?";

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, role.name());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Utilisateur utilisateur = new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("telephone"),
                        Role.valueOf(rs.getString("role"))
                );

                utilisateurs.add(utilisateur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateurs;
    }


    // =========================
    // METTRE À JOUR
    // =========================
    @Override
    public void update(int id,
                       String nom,
                       String prenom,
                       String email,
                       String telephone) {

        String sql = """
                UPDATE utilisateur
                SET nom = ?, prenom = ?, email = ?, telephone = ?
                WHERE id = ?
                """;

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, telephone);
            stmt.setInt(5, id);

            stmt.executeUpdate();

            System.out.println("Utilisateur mis à jour.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // METTRE À JOUR MOT DE PASSE
    // =========================
    @Override
    public void updateMotDePasse(int id, String nouveauMotDePasse) {

        String sql = """
                UPDATE utilisateur
                SET motDePasse = ?
                WHERE id = ?
                """;

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nouveauMotDePasse);
            stmt.setInt(2, id);

            stmt.executeUpdate();

            System.out.println("Mot de passe mis à jour.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // SUPPRIMER
    // =========================
    @Override
    public int delete(int id) {

        String sql = "DELETE FROM utilisateur WHERE id = ?";

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // =========================
    // EXISTE PAR EMAIL
    // =========================
    @Override
    public boolean existsByEmail(String email) {

        String sql = "SELECT COUNT(*) FROM utilisateur WHERE email = ?";

        try (
                Connection conn = ConnectBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}




 */