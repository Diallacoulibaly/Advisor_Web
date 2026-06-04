package main.java.model.service;

import main.java.model.classes.SuivieEtape;
import main.java.model.enums.StatutEtape;

public interface SuivieEtapeService {
    public void ajout(SuivieEtape suivieEtape);

    public void changerStatut(int id, StatutEtape statutEtape);
}
