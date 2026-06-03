package main.java.model.service;

import main.java.model.classes.Historique;

import java.util.List;

public interface HistoriqueService {
    int ajouterHistorique(Historique historique);
    List<Historique> afficherHistorique();
    void supprimerHistorique(int id);
}