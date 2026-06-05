package main.java.model.dao;

import main.java.model.classes.ProjetClient;
import main.java.model.classes.SuivieEtape;
import main.java.model.enums.StatutEtape;
import main.java.model.enums.StatutProjet;

import java.util.List;
import java.util.Optional;

public interface SuivieEtapeDao {
    public void ajout(SuivieEtape suivieEtape);

   public void changerStatut(int id, StatutEtape statutEtape);



    /*public SuivieEtape getByIdClient(int id);
    List<SuivieEtape> getByClient(int idClient);*/




}
