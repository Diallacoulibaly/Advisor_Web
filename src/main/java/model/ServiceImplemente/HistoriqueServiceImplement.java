package main.java.model.ServiceImplemente;

import main.java.model.classes.Historique;
import main.java.model.dao.HistoriqueDao;
import main.java.model.service.HistoriqueService;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueServiceImplement implements HistoriqueService {
    private HistoriqueDao historiqueDao;
    public HistoriqueServiceImplement (HistoriqueDao historiqueDao){
        this.historiqueDao = historiqueDao;
    }

    @Override
    public void ajouterHistorique(Historique historique) {
        historiqueDao.ajouterHistorique(historique);

    }

    @Override
    public List<Historique> afficherHistorique() {
        return historiqueDao.afficherHistorique();
    }

    @Override
    public void supprimerHistorique(int id) {
        if(id <= 0){
            System.out.println("Erreur id historique incorrecte !!");
            return;
        }
        historiqueDao.supprimerHistorique(id);

    }
}