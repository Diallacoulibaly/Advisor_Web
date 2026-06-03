package main.java.model.dao;

import main.java.model.classes.Historique;

import java.util.List;

public interface HistoriqueDao {
    int ajouterHistorique(Historique historique);
    List<Historique> afficherHistorique();
    void supprimerHistorique(int id);
}