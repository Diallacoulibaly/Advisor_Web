package main.java.model.DaoImplement;

import main.java.model.classes.Historique;
import main.java.model.classes.HistoriqueProjet;
import main.java.model.classes.Projet;
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

            ps.setInt(1, hp.getHistorique().getId());
            ps.setInt(2, hp.getProjet().getId());

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
                Historique h=new Historique();
                h.setId( rs.getInt("idHistorique"));
                Projet p=new Projet();
                p.setId(rs.getInt("idProjet"));
                HistoriqueProjet hp = new HistoriqueProjet(h, p);
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
        String sql = "SELECT distinct idProjet FROM historiqueprojet WHERE idHistorique = ?";

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
