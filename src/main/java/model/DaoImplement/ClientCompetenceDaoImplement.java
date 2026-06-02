//package main.java.model.dao.daoimplementation;
//
//import main.java.Database.ConnectBD;
//import main.java.model.classes.ClientCompetence;
//import main.java.model.dao.ClientCompetenceDao;
//
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClientCompetenceDaoImplement implements ClientCompetenceDao {
//
//    Connection conn = ConnectBD.getConnection();
//
//    //  ADD
//    @Override
//    public void add(ClientCompetence cc) {
//
//        String sql = "INSERT INTO client_competence (idClient, idCompetence) VALUES (?, ?)";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, cc.getIdClient());
//            ps.setInt(2, cc.getIdCompetence());
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //  GET ALL
//    @Override
//    public List<ClientCompetence> getAll() {
//
//        List<ClientCompetence> list = new ArrayList<>();
//
//        String sql = "SELECT * FROM client_competence";
//
//        try (Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//
//            while (rs.next()) {
//
//                ClientCompetence cc = new ClientCompetence(
//                        rs.getInt("idClient"),
//                        rs.getInt("idCompetence")
//                );
//
//                list.add(cc);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//
//    //  UPDATE
//    @Override
//    public void update(ClientCompetence cc, int oldIdClient, int oldIdCompetence) {
//
//        String sql = "UPDATE client_competence SET idClient=?, idCompetence=? " +
//                "WHERE idClient=? AND idCompetence=?";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            // nouvelles valeurs
//            ps.setInt(1, cc.getIdClient());
//            ps.setInt(2, cc.getIdCompetence());
//
//            // anciennes valeurs
//            ps.setInt(3, oldIdClient);
//            ps.setInt(4, oldIdCompetence);
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //  DELETE
//    @Override
//    public void delete(int idClient, int idCompetence) {
//
//        String sql = "DELETE FROM client_competence WHERE idClient=? AND idCompetence=?";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idClient);
//            ps.setInt(2, idCompetence);
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
