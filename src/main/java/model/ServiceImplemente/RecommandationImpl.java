package main.java.model.ServiceImplemente;

import main.java.model.classes.Client;
import main.java.model.classes.Projet;
import main.java.model.dao.ClientCompetenceDao;
import main.java.model.dao.CompetenceProjetDao;
import main.java.model.dao.ProjetDao;
import main.java.model.enums.Niveau;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecommandationImpl {

    ProjetDao projetDao;
    CompetenceProjetDao competenceProjetDao;
    ClientCompetenceDao clientCompetenceDao;

    public RecommandationImpl(ProjetDao pr, CompetenceProjetDao cpr, ClientCompetenceDao ccr) {
        projetDao = pr;
        competenceProjetDao = cpr;
        clientCompetenceDao = ccr;
    }

    public int matchNiveau (Niveau niveau) {
        int x = 0;
        switch (niveau) {
            case DEBUTANT -> { x = 1; }
            case INTERMEDIAIRE -> { x = 2; }
            case EXPERT -> { x = 3; }
        }
        return x;
    }

    public List<Projet> suggererProjets(Client client) throws SQLException {

        List<Projet> projetsRecommandes = new ArrayList<>();
        List<Projet> tousLesProjets = projetDao.getAll();
        List<Integer> competencesClient = clientCompetenceDao.getSkillsByClient(client.getIdUtilisateur());

        for (Projet projet : tousLesProjets) {
            if (client.getBudgetApporte() >= projet.getBudgetMin()) {

                List<Integer> competencesProjet = competenceProjetDao.rech_CP(projet.getId());

                boolean competenceTrouvee = false;
                boolean niveauCompatible = false;
                boolean localiteCompatible = false;
                boolean domaineCompatible = false;

                // Vérifier si une compétence correspond
                for (Integer competence : competencesProjet) {
                    if (competencesClient.contains(competence)) {
                        competenceTrouvee = true;
                        break;
                    }
                }
                // Si aucune compétence demandée, on suppose que tout le monde peut faire ce projet
                if (competencesProjet.isEmpty()) { competenceTrouvee = true; }

                if (matchNiveau(projet.getNiveau()) <= matchNiveau(client.getNiveau())) niveauCompatible = true;

                if (projet.getLocalite().equals(client.getLocalite())) localiteCompatible = true;

                if (projet.getDomaine().equals(client.getDomaine())) domaineCompatible = true;

                if (competenceTrouvee && niveauCompatible && localiteCompatible && domaineCompatible) {
                    projetsRecommandes.add(projet);
                }
            }
        }

        return projetsRecommandes;
    }
}