package main.java.model.service;

import main.java.model.classes.Historique;
import main.java.model.classes.HistoriqueProjet;

import java.util.List;

public interface HistoriqueService {
    int ajouterHistorique(Historique historique);
    List<Historique> afficherHistorique();
    List<HistoriqueProjet> afficherHistoriqueClient(int idClient);
    void supprimerHistorique(int id);
    List<Integer> getProjetIdsFromHist(int idClient);
}