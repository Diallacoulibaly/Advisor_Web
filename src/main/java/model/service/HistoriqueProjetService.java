package main.java.model.service;

import main.java.model.classes.HistoriqueProjet;

import java.util.List;

public interface HistoriqueProjetService {

    void add(HistoriqueProjet hp);
    List<HistoriqueProjet> getAll();
    List<Integer> getProjetsByHist(int idHistorique);

}
