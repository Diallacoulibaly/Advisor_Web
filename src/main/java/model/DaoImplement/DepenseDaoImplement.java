package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Depense;
import main.java.model.dao.DepenseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepenseDaoImplement implements DepenseDao {

    // ─── Mapper : transforme un ResultSet en objet Depense ───────────────────
    private Depense mapRow(ResultSet rs) throws SQLException {
        return new Depense(
                rs.getInt("id"),
                rs.getDouble("montant"),
                rs.getString("description"),
                rs.getDate("date"),
                rs.getInt("idActivite")
        );
    }

    // ─── ADD ─────────────────────────────────────────────────────────────────
    @Override
    public void add(Depense depense) {
        String sql = "INSERT INTO depense (montant, description, date, idActivite) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, depense.getMontant());
            ps.setString(2, depense.getDescription());
            ps.setDate(3,   depense.getDate());
            ps.setObject(4, depense.getIdActivite()); // setObject gère le null proprement
            ps.executeUpdate();
            System.out.println("Dépense ajoutée avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur ajout dépense : " + e.getMessage());
        }
    }

    // ─── GET BY ID ───────────────────────────────────────────────────────────
    @Override
    public Optional<Depense> getById(int id) {
        String sql = "SELECT * FROM depense WHERE id = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); // executeQuery et non executeUpdate !
            if (rs.next()) return Optional.of(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Erreur getById dépense : " + e.getMessage());
        }
        return Optional.empty();
    }

    // ─── GET ALL ─────────────────────────────────────────────────────────────
    @Override
    public List<Depense> getAll() {
        List<Depense> depenses = new ArrayList<>();
        String sql = "SELECT * FROM depense"; // "SELECT all" n'est pas du SQL valide
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) depenses.add(mapRow(rs));

        } catch (SQLException e) {
            System.out.println("Erreur getAll dépenses : " + e.getMessage());
        }
        return depenses;
    }

    // ─── UPDATE ──────────────────────────────────────────────────────────────
    @Override
    public void update(int id, double montant, String description, Date date, Integer idActivite) {
        String sql = "UPDATE depense SET montant=?, description=?, date=?, idActivite=? WHERE id=?";
        // Virgule en trop supprimée dans le WHERE de votre version originale
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, montant);
            ps.setString(2, description);
            ps.setDate(3,   date);
            ps.setObject(4, idActivite);
            ps.setInt(5,    id);

            int lines = ps.executeUpdate();
            System.out.println(lines > 0 ? "Dépense modifiée." : "Aucune dépense modifiée.");

        } catch (SQLException e) {
            System.out.println("Erreur update dépense : " + e.getMessage());
        }
    }

    // ─── DELETE ──────────────────────────────────────────────────────────────
    @Override
    public int delete(int id) {
        String sql = "DELETE FROM depense WHERE id = ?";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int lines = ps.executeUpdate();
            System.out.println(lines > 0 ? "Dépense supprimée." : "Aucune dépense trouvée.");
            return lines;

        } catch (SQLException e) {
            System.out.println("Erreur suppression dépense : " + e.getMessage());
        }
        return 0;
    }
}