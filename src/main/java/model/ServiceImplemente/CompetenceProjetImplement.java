package main.java.model.ServiceImplemente;

import main.java.model.dao.CompetenceProjetDao;
import main.java.model.service.CompetenceProjetService;
import main.java.model.classes.CompetenceProjet;
import java.util.List;

public class CompetenceProjetImplement implements CompetenceProjetService {

    private final CompetenceProjetDao competenceProjetDao;

    public CompetenceProjetImplement(CompetenceProjetDao competenceProjetDao) {
        this.competenceProjetDao = competenceProjetDao;
    }

    @Override
    public boolean ajouter(CompetenceProjet cp) {
        if (cp == null) {
            System.out.println("L'association Competence-Projet ne peut pas être nulle.");
            return false;
        }
        if (cp.getCompetenceId() <= 0 || cp.getIdProjet() <= 0) {
            System.out.println("Les IDs de compétence et de projet doivent être positifs.");
            return false;
        }
        return competenceProjetDao.add_CP(cp);
    }

    @Override
    public CompetenceProjet rechercher(int idProjet, int idCompetence) {
        if (idProjet <= 0 || idCompetence <= 0) {
            System.out.println("Les IDs doivent être des entiers positifs.");
            return null;
        }
        return competenceProjetDao.rech_CP(idProjet, idCompetence);
    }

    @Override
    public List<Integer> listerIdsCompetencesParProjet(int idProjet) {
        if (idProjet <= 0) {
            System.out.println("L'ID du projet doit être un entier positif.");
            return List.of();
        }
        return competenceProjetDao.rech_CP(idProjet);
    }

    @Override
    public List<CompetenceProjet> lister() {
        return competenceProjetDao.ListeCP();
    }

    @Override
    public boolean miseAjour(int ancienIdProjet, int ancienIdCompetence, CompetenceProjet nouvelleAssociation) {
        if (ancienIdProjet <= 0 || ancienIdCompetence <= 0) {
            System.out.println("Les anciens identifiants doivent être positifs.");
            return false;
        }
        if (nouvelleAssociation == null || nouvelleAssociation.getIdProjet() <= 0 || nouvelleAssociation.getCompetenceId() <= 0) {
            System.out.println("La nouvelle association est invalide ou incomplète.");
            return false;
        }
        return competenceProjetDao.mise_a_jour_CP(ancienIdProjet, ancienIdCompetence, nouvelleAssociation);
    }

    @Override
    public boolean supprimer(int idProjet, int idCompetence) {
        if (idProjet <= 0 || idCompetence <= 0) {
            System.out.println("Les identifiants doivent être positifs.");
            return false;
        }
        return competenceProjetDao.suppr_CP(idProjet, idCompetence);
    }
}
