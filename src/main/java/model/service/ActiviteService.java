package main.java.model.service;

import main.java.model.classes.Activite;
import main.java.model.classes.Etape;

import java.util.List;

public interface ActiviteService {
    void ajouterActivite(Activite activite);
    List<Activite> afficherActivite();
    List<Activite> getActiviteByEtape(int idEtape);
    void modifierActivite(Activite activite);
    void marquerTerminer(int id);
    void supprimerActivite(int id);
}