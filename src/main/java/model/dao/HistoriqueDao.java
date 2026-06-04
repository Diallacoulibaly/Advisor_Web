package main.java.model.dao;

import main.java.model.classes.Historique;
import main.java.model.classes.HistoriqueProjet;
import main.java.model.classes.Projet;

import java.util.List;
import java.util.Map;

public interface HistoriqueDao {
    int ajouterHistorique(Historique historique);
    List<Historique> afficherHistorique();
    List<HistoriqueProjet> afficherHistoriqueClient(int idClient);
    void supprimerHistorique(int id);
}