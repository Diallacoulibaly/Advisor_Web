package main.java.model.ServiceImplemente;

import main.java.model.classes.HistoriqueProjet;
import main.java.model.dao.HistoriqueProjetDao;
import main.java.model.service.HistoriqueProjetService;

import java.sql.SQLException;
import java.util.List;

public class HistoriqueProjetServiceImplement implements HistoriqueProjetService {



    private final HistoriqueProjetDao historiqueProjetDao;

    public HistoriqueProjetServiceImplement(HistoriqueProjetDao historiqueProjetDao) {
        this.historiqueProjetDao = historiqueProjetDao;
    }

    @Override
    public void add(HistoriqueProjet historiqueProjet) {

        if (historiqueProjet.getIdCompetence() == null || historiqueProjet.getIdClient()==null ) {
            System.out.println("Erreur : objet HistoriqueProjet null");
            return;
        }
        historiqueProjetDao.add(historiqueProjet);
    }

    @Override
    public List<HistoriqueProjet> getAll() {
        return historiqueProjetDao.getAll();
    }


    @Override
    public List<Integer> getProjetsByHist(int id) {
        return historiqueProjetDao.getProjetsByHist(id);
    }
}

