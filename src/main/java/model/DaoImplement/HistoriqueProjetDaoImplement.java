package main.java.model.DaoImplement;

import main.java.model.classes.HistoriqueProjet;
import main.java.model.dao.HistoriqueProjetDao;
import main.java.Database.ConnectBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueProjetDaoImplement implements HistoriqueProjetDao {

    Connection conn = ConnectBD.getConnection();

    //  ADD
    @Override
    public void add(HistoriqueProjet hp) {

        String sql = "INSERT INTO historiqueprojet (idHistorique, idProjet) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, hp.getIdClient());
            ps.setInt(2, hp.getIdCompetence());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //  GET ALL
    @Override
    public List<HistoriqueProjet> getAll() {

        List<HistoriqueProjet> list = new ArrayList<>();

        String sql = "SELECT * FROM historiqueprojet";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                HistoriqueProjet hp = new HistoriqueProjet(
                        rs.getInt("idHistorique"),
                        rs.getInt("idProjet")
                );

                list.add(hp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    

    @Override
    public List<Integer> getProjetsByHist(int idHistorique) {
        List<Integer> projetIds = new ArrayList<>();
        String sql = "SELECT idProjet FROM historiqueprojet WHERE idHistorique = ?";

        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHistorique);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    projetIds.add(rs.getInt("idProjet"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur getProjetsByHist : " + e.getMessage());
        }
        return projetIds;
    }
}
