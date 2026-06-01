package main.java.model.ServiceImplemente;

import main.java.model.classes.Competence;
import main.java.model.dao.CompetenceDao;
import main.java.model.service.CompetenceService;

import java.util.List;

public class CompetenceServiceImplement implements CompetenceService {

    private final CompetenceDao competenceDao;

    public CompetenceServiceImplement(CompetenceDao competenceDao) {
        this.competenceDao = competenceDao;
    }

    @Override
    public void ajouter(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println("Le nom de la compétence est obligatoire");
            return;
        }
        competenceDao.ajouterCompetence(new Competence(nom.trim()));
    }

    @Override
    public void modifier(int id, String nom) {
        if (id <= 0) {
            System.out.println("ID invalide");
            return;
        }
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println("Le nom de la compétence est obligatoire");
            return;
        }
        competenceDao.modifierCompetence(new Competence(id, nom.trim()));
    }

    @Override
    public void supprimer(int id) {
        competenceDao.supprimerCompetence(id);
    }

    @Override
    public List<Competence> afficher() {
        return competenceDao.afficherCompetences();
    }

    @Override
    public Competence getById(int id) {
        return competenceDao.getById(id);
    }
}
