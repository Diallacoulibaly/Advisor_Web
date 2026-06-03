package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.*;
import main.java.model.dao.SuivieEtapeDao;
import main.java.model.enums.StatutEtape;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuivieEtapeDaoImplement implements SuivieEtapeDao {
    @Override
    public void ajout(SuivieEtape suivieEtape) {
        String sql = "INSERT INTO suivieEtape(idClient,idEtape,status) Values(?,?,?)";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)
        ) {
            stml.setInt(1, suivieEtape.getClient().getIdUtilisateur());
            stml.setInt(2, suivieEtape.getEtape().getIdEtape());
            stml.setString(3, StatutEtape.ENCOURS.name());
            stml.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changerStatut(int id, StatutEtape statutEtape) {
        String sql = "UPDATE suivieEtape set statut=? Where id=? ";
        try (Connection cnn = ConnectBD.getConnection();
             PreparedStatement stml = cnn.prepareStatement(sql)) {
            stml.setString(1, statutEtape.name());
            stml.setInt(2, id);
            stml.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
