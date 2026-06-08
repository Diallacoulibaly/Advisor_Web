package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.dao.ActiviteCloentDao;
import main.java.model.enums.Statut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActiviteClientDaoImplement implements ActiviteCloentDao {
    @Override
    public void marquerTerminer(int id) {
        String sql = "UPDATE activite SET statut=? WHERE idActivite=?";

        try (Connection connection = ConnectBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, String.valueOf(Statut.TERMINE));
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("Activité marquée comme terminer avec succès ! ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
