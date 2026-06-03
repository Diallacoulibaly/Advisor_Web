package main.java.model.dao;

import main.java.model.classes.HistoriqueProjet;

import java.sql.SQLException;
import java.util.List;

public interface HistoriqueProjetDao {
    void add(HistoriqueProjet hp);
    List<HistoriqueProjet> getAll();
    List<Integer> getProjetsByHist(int idHistorique);
}