package main.java.model.service;

import main.java.model.classes.SuivieEtape;
import main.java.model.enums.StatutEtape;

import java.util.List;

public interface SuivieEtapeService {
    public void ajout(SuivieEtape suivieEtape);
    public void changerStatut(int id, StatutEtape statutEtape);
    List<SuivieEtape> obtenirSuivisParProjetEtClient(int idProjet, int idClient);
    void validerEtapeEtOuvrirSuivante(int idEtape, int idClient);
}
