package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.Commentaire;
import main.java.model.classes.Domaine;
import main.java.model.dao.CommentaireDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDaoImplement implements CommentaireDao {
    @Override
    public void ajoutCmt(Commentaire commentaire){
        String sql="INSERT INTO commentaire (message) VALUE(?)";
        try(Connection cnn= ConnectBD.getConnection();
            PreparedStatement stml=cnn.prepareStatement(sql)) {
            stml.setString(1,commentaire.getMessage());
            stml.executeUpdate();
            System.out.println("Commentaire ajouté avec succès");

        }
        catch (Exception e){
            System.out.println("erreur lors de l'ajout du commentaire");
            e.printStackTrace();
        }

    }
    @Override
    public List<Commentaire> afficherCmt(){
        List<Commentaire> commentaireList=new ArrayList<>();
        String sql="SELECT * FROM commentaire";
        try(Connection cnn=ConnectBD.getConnection();
            Statement stml=cnn.createStatement();
            ResultSet rs=stml.executeQuery(sql)){
            while (rs.next()){
                Commentaire d=new Commentaire(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getDate("date_cmt")
                );
                commentaireList.add(d);

            }



        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  commentaireList;

    }
    @Override
    public Commentaire getById(int id){
        String sql="SELECT * FROM commentaire where id=?";
        try (Connection connection = ConnectBD.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Commentaire commentaire = new Commentaire(
                            rs.getInt("id"),
                            rs.getString("message"),
                            rs.getDate("date_cmt")
                    );
                    return commentaire;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation du commentaire" + e.getMessage());
        }
        return null;}
    @Override
    public void modifierCmt(Commentaire commentaire){
        String sql="UPDATE commentaire SET message=? WHERE id=?";
        try (Connection cnn=ConnectBD.getConnection();
             PreparedStatement stml=cnn.prepareStatement(sql)){
            stml.setString(1,commentaire.getMessage());
            stml.setInt(2,commentaire.getId());
            stml.executeUpdate();
            System.out.println(("commentaire modifié avec succes"));

        }
        catch (Exception e){
            System.out.println(("Domaine non modifié "));

            e.printStackTrace();
        }
    }
    @Override
    public void supprimerCmt(int id){
        String sql="DELETE  FROM message WHERE id=?";
        try(Connection cnn=ConnectBD.getConnection();
            PreparedStatement stml=cnn.prepareStatement(sql)) {
            stml.setInt(1,id);
            stml.executeUpdate();
            System.out.println("Commentaire supprimé avec succes");

        }
        catch (Exception e){
            System.out.println("Commentaire non supprimé");
        }
    }
}
