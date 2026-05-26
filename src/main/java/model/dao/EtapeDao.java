package main.java.model.dao;

import java.util.List;
import java.util.Optional;
import main.java.model.classes.Etape;

//Interface
public interface EtapeDao {


    void ajout_etape(Etape etape);
    Optional<Etape> rech_etape(int idEtape);
    List<Etape> Liste_etape();
    boolean mise_a_jour_etape(Etape etape);
    boolean suppr_etape(int idEtape);
    boolean verif_etape(int idEtape);
}
