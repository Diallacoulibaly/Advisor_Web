package main.java.model.ServiceImplemente;

import main.java.model.classes.SuivieEtape;
import main.java.model.dao.SuivieEtapeDao;
import main.java.model.enums.StatutEtape;
import main.java.model.service.SuivieEtapeService;
import java.util.ArrayList;
import java.util.List;

public class SuivieEtapeServiceImplement implements SuivieEtapeService {
    private SuivieEtapeDao suivieEtapeDao;

    public SuivieEtapeServiceImplement(SuivieEtapeDao suivieEtapeDao) {
        this.suivieEtapeDao = suivieEtapeDao;
    }

    @Override
    public void changerStatut(int id, StatutEtape statutEtape) {
        suivieEtapeDao.changerStatut(id, statutEtape);
    }

    @Override
    public void ajout(SuivieEtape suivieEtape) {
        Integer idEtape = suivieEtape.getEtape().getIdEtape();
        Integer idClient = suivieEtape.getClient().getIdUtilisateur();
        if (idEtape == null || idClient == null) {
            System.out.println("Erreur !!!");
            return;
        }
        suivieEtapeDao.ajout(suivieEtape);
    }

    // Implémentation de la méthode pour le contrôleur
    @Override
    public List<SuivieEtape> obtenirSuivisParProjetEtClient(int idProjet, int idClient) {
        if (idProjet <= 0 || idClient <= 0) {
            return new ArrayList<>();
        }
        return suivieEtapeDao.trouverParProjetEtClient(idProjet, idClient);
    }

    @Override
    public void validerEtapeEtOuvrirSuivante(int idEtape, int idClient) {
        if (idEtape <= 0 || idClient <= 0) {
            return;
        }
        suivieEtapeDao.validerEtapeEtOuvrirSuivante(idEtape, idClient);
    }

}
