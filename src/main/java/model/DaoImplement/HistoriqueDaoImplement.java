package main.java.model.DaoImplement;

import main.java.Database.ConnectBD;
import main.java.model.classes.*;
import main.java.model.dao.HistoriqueDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoriqueDaoImplement implements HistoriqueDao {
    @Override
    public int ajouterHistorique(Historique historique) {
        String sql = "INSERT INTO historique (idClient, descriptionAction ) VALUES (?, ?)";
        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setInt(1, historique.getIdClient());
            ps.setString(2, historique.getDescriptionAction());

            ps.executeUpdate();
            try(ResultSet rs= ps.getGeneratedKeys()) {
                if(rs.next()) {
                    return rs.getInt(1);
                }

            }
            System.out.println("Historique ajoutée avec succès !");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Historique> afficherHistorique() {
        String sql = "SELECT * FROM historique";
        List<Historique> historiques = new ArrayList<>();

        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                Historique historique = new Historique();
                historique.setId(rs.getInt("id"));
                historique.setDate(rs.getDate("date").toLocalDate());
                historique.setIdClient(rs.getInt("idClient"));
                historique.setDescriptionAction(rs.getString("descriptionAction"));

                historiques.add(historique);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return historiques;
    }

    @Override public List<HistoriqueProjet> afficherHistoriqueClient(int idClient) { String sql = "SELECT p.id as idP, p.titre as titre, p.description as description, p.duree as duree, p.budgetMin as budgetMin, p.budgetMax as budgetMax, h.date as dateH,l.regionClient as regionClient ,d.domaine as domaine FROM client cl INNER JOIN historique h ON h.idClient = cl.id INNER JOIN historiqueProjet as ph ON ph.idHistorique = h.id INNER JOIN projet as p ON p.id = ph.idProjet INNER JOIN domaine AS d ON d.id=p.idDomaine INNER JOIN localite AS l ON l.id=p.idLocalite WHERE cl.id = ?";
        List<HistoriqueProjet> historiquesP = new ArrayList<>();
        try(Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql) )
        { ps.setInt(1, idClient);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                HistoriqueProjet historiqueProjet= new HistoriqueProjet();
                Projet projet = new Projet();
                Historique historique= new Historique();
                Domaine d=new Domaine(); Localite l=new Localite();
                historique.setDate(rs.getDate("dateH").toLocalDate());
                d.setDomaine(rs.getString("domaine"));
                l.setRegionClient(rs.getString("regionClient"));
                projet.setId(rs.getInt("idP"));
                projet.setTitre(rs.getString("titre"));
                projet.setDescription(rs.getString("description"));
                projet.setDuree(rs.getInt("duree"));
                projet.setBudgetMin(rs.getInt("budgetMin"));
                projet.setBudgetMin(rs.getInt("budgetMax"));
                projet.setDomaine(d); projet.setLocalite(l);
                historiqueProjet.setHistorique(historique);
                historiqueProjet.setProjet(projet);
                historiquesP.add(historiqueProjet);
            } } catch (SQLException e)
        { throw new RuntimeException(e);
        } return historiquesP; }
    @Override
    public void supprimerHistorique(int id) {

        String sql = "DELETE FROM historique WHERE id=?";

        try (Connection connection = ConnectBD.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}