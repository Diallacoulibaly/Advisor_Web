package main.java.model.DaoImplement;


import main.java.Database.ConnectBD;
import main.java.model.classes.*;
import main.java.model.classes.Activite;
import main.java.model.dao.DepenseDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepenseDaoImplement implements DepenseDao {

    private Depense mapRow(ResultSet rs) throws SQLException {
        Activite activite = new Activite();
        Client client= new Client();
        activite.setId(rs.getInt("idActivite"));
        return new Depense(
                rs.getInt("id"),
                rs.getDouble("montant"),
                rs.getString("description"),
                rs.getDate("date"),
                activite,
                client
        );
    }

    @Override
    public void add(Depense depense) {
        String sql = "INSERT INTO depense (montant, description, date, idActivite,idClient) VALUES (?,?, ?, ?, ?)";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, depense.getMontant());
            ps.setString(2, depense.getDescription());
            ps.setDate(3, depense.getDate());
            // CORRECTION: getActivite().getId() au lieu de getActivite() directement
            ps.setInt(4, depense.getActivite().getId());
            ps.setInt(5,depense.getClient().getIdUtilisateur());
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());;
            }
            System.out.println("Dépense ajoutée avec succès.");

        } catch (SQLException e) {
            System.out.println("Erreur ajout dépense : " + e.getMessage());
        }
    }
    public double getTotalDepenseClient(int idClient) {
        String sql =
                "SELECT SUM(montant) AS total " +
                        "FROM depense " +
                        "WHERE idClient = ?";

        try(Connection conn = ConnectBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idClient);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
@Override
public Optional<Depense> getById(int id) {
    String sql = "SELECT * FROM depense WHERE id = ?";
    try (Connection conn = ConnectBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return Optional.of(mapRow(rs));

    } catch (SQLException e) {
        System.out.println("Erreur getById dépense : " + e.getMessage());
    }
    return Optional.empty();
}

@Override
public List<Depense> getAll() {
    List<Depense> depenses = new ArrayList<>();
    String sql = "SELECT * FROM depense";
    try (Connection conn = ConnectBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ResultSet rs = ps.executeQuery();
        while (rs.next()) depenses.add(mapRow(rs));

    } catch (SQLException e) {
        System.out.println("Erreur getAll dépenses : " + e.getMessage());
    }
    return depenses;
}

// CORRECTION: update doit recevoir un objet Depense complet
@Override
public void update(Depense depense) {
    String sql = "UPDATE depense SET montant = ?, description = ?, date = ?, idActivite = ? ,idClient=? WHERE id = ?";

    try (Connection conn = ConnectBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDouble(1, depense.getMontant());
        ps.setString(2, depense.getDescription());
        ps.setDate(3, depense.getDate());
        ps.setInt(4, depense.getActivite().getId());
        ps.setInt(5, depense.getIdDepense());
        ps.setInt(6,depense.getClient().getIdUtilisateur());

        int lines = ps.executeUpdate();
        System.out.println(lines > 0 ? "Dépense modifiée." : "Aucune dépense modifiée.");

    } catch (SQLException e) {
        System.out.println("Erreur update dépense : " + e.getMessage());
    }
}

@Override
public void delete(int id) {
    String sql = "DELETE FROM depense WHERE id = ?";
    try (Connection conn = ConnectBD.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        int lines = ps.executeUpdate();
        System.out.println(lines > 0 ? "Dépense supprimée." : "Aucune dépense trouvée.");

    } catch (SQLException e) {
        System.out.println("Erreur suppression dépense : " + e.getMessage());
    }
}
}
