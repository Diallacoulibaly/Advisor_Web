package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Domaine;
import main.java.model.dao.DomaineDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomaineDaoImplement implements DomaineDao {
    @Override
    public void ajoutDomaine(Domaine domaine){
        String sql="INSERT INTO domaine(domaine) VALUE(?)";
        try(Connection cnn= ConnectBD.getConnection();
            PreparedStatement stml=cnn.prepareStatement(sql)) {
            stml.setString(1,domaine.getDomaine());
            stml.executeUpdate();
            System.out.println("Domaine ajouté avec succès");

        }
        catch (Exception e){
            System.out.println("erreur lors de l'ajout du domaine");
            e.printStackTrace();
        }

    }
    @Override
    public List<Domaine> afficherDomaine(){
        List<Domaine> domaineList=new ArrayList<>();
        String sql="SELECT * FROM domaine";
        try(Connection cnn=ConnectBD.getConnection();
            Statement stml=cnn.createStatement();
            ResultSet rs=stml.executeQuery(sql)){
            while (rs.next()){
                Domaine d=new Domaine(
                        rs.getInt("id"),
                        rs.getString("domaine")
                );
                domaineList.add(d);

            }



        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  domaineList;

    }
    @Override
    public void modifierDomaine(Domaine domaine){
        String sql="UPDATE domaine SET domaine=? WHERE id=?";
        try (Connection cnn=ConnectBD.getConnection();
             PreparedStatement stml=cnn.prepareStatement(sql)){
            stml.setString(1,domaine.getDomaine());
            stml.setInt(2,domaine.getId());
            stml.executeUpdate();
            System.out.println(("Domaine modifié avec succes"));

        }
        catch (Exception e){
            System.out.println(("Domaine non modifié "));

            e.printStackTrace();
        }
    }
    @Override
    public void supprimerDomaine(int id){
        String sql="DELETE  FROM domaine WHERE id=?";
        try(Connection cnn=ConnectBD.getConnection();
            PreparedStatement stml=cnn.prepareStatement(sql)) {
            stml.setInt(1,id);
            stml.executeUpdate();
            System.out.println("Domaine supprimé avec succes");

        }
        catch (Exception e){
            System.out.println("Domaine non supprimé");
        }
    }
}
