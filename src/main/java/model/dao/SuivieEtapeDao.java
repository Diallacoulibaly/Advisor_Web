package main.java.model.dao;

import main.java.model.classes.SuivieEtape;
import main.java.model.enums.StatutEtape;
import java.util.List;

public interface SuivieEtapeDao {
    void ajout(SuivieEtape suivieEtape);
    void changerStatut(int id, StatutEtape statutEtape);
    void validerEtapeEtOuvrirSuivante(int idEtape, int idClient);
    List<SuivieEtape> trouverParProjetEtClient(int idProjet, int idClient);
}
