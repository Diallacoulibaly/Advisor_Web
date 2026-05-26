package main.java.model.service;

import main.java.model.classes.Activite;

import java.util.List;

public interface ActiviteService {
    void ajouterActivite(Activite activite);
    List<Activite> afficherActivite();
    void modifierActivite(Activite activite);
    void marquerTerminer(int id);
    void supprimerActivite(int id);
}