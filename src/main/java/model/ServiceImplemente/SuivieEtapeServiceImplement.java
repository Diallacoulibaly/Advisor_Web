package main.java.model.ServiceImplemente;

import main.java.model.classes.Domaine;
import main.java.model.classes.SuivieEtape;
import main.java.model.dao.SuivieEtapeDao;
import main.java.model.enums.StatutEtape;
import main.java.model.enums.StatutProjet;
import main.java.model.service.SuivieEtapeService;

public class SuivieEtapeServiceImplement implements SuivieEtapeService {
    private SuivieEtapeDao suivieEtapeDao;
    public  SuivieEtapeServiceImplement(SuivieEtapeDao suivieEtapeDao){
        this.suivieEtapeDao=suivieEtapeDao;

    }

    @Override
    public void changerStatut(int id, StatutEtape statutEtape) {
        suivieEtapeDao.changerStatut(id,statutEtape);
    }
    public void ajout(SuivieEtape suivieEtape){
        Integer idEtape=suivieEtape.getEtape().getIdEtape();
        Integer idClient=suivieEtape.getClient().getIdUtilisateur();
        if (idEtape == null || idClient==null) {
            System.out.println("Erreur !!!");
            return;
        }
        suivieEtapeDao.ajout(suivieEtape);
    }


}
