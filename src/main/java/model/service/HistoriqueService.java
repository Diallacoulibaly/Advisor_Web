package main.java.model.service;

import main.java.model.classes.Historique;

import java.util.List;

public interface HistoriqueService {
    void ajouterHistorique(Historique historique);
    List<Historique> afficherHistorique();
    void supprimerHistorique(int id);
}