package DaoImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Database.ConnectBD;
import classes.Activite;
import classes.Depense;
import Dao.DepenseDao;


public class DepenseDaoImplement implements DepenseDao {

    @Override
    public void add(Depense depense) {
        // TODO Auto-generated method stub
        String sql="insert into Depense(montant,description,date,activite) VALUES(?,?,?,?)";
        try (Connection conn = ConnectBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setDouble(1,depense.getMontant());
            ps.setString(2, depense.getDescription());
            ps.setDate(3, depense.getDate());
            ps.setInt(4, depense.getActivite().getId());
            ps.executeUpdate();
            System.out.println("Depense ajoute");

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("Erreur lors de l'ajout du depense : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Depense> getById(int id) {
        String sql ="select from Depense where(id=?)";
        try(Connection conn = ConnectBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            ps.executeUpdate();

        }
        catch (SQLException e){
            System.out.println("Obtenir le depense par l'id : "+e.getMessage());
            e.getMessage();

        }

        return null;

    }

    @Override
    public List<Depense> getAll() {

        String sql ="SELECT all FROM Depense";
        try(
                Connection conn = ConnectBD.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        )
        {ResultSet rs =  ps.executeQuery();
            List<Depense> depenses = new ArrayList<>();
            while (rs.next()) {
                Depense depense = new Depense(null, 0, sql, null, null);
                depense.setMontant(rs.getDouble(sql));
                depense.setDescription(rs.getString(sql));
                depense.setDate(rs.getDate(sql));
                depense.setActivite((Activite) rs.getObject("activite"));

            }

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println("Afficher une liste de tout les depense : "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(int id) {

        String sql="update Depense set montant = ?, description = ?, date = ?, activite = ?, WHERE id = ?";
        try(Connection conn = ConnectBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(5, id);
            int line = ps.executeUpdate();
            if (line > 0) {
                System.out.println("depense modifier avec succès");
            }else{
                System.out.println("la depense n'a pas ete modifier");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du depense : "+ e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        String sql="delete from Depense WHERE(id=?)";
        try(Connection conn = ConnectBD.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("depense supprimée");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du depense : "+ e.getMessage());
            e.printStackTrace();
        }
    }


}