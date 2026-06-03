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

    public int matchNiveau (String niveau) {
        switch (niveau) {
            case "INTERMEDIAIRE" -> { return 2; }
            case "EXPERT" -> { return  3; }
            default -> { return 1; }
        }
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
                        System.out.println(projet.getId()+ "Competence matche");
                        break;
                    }
                }
                // Si aucune compétence demandée, on suppose que tout le monde peut faire ce projet
                if (competencesProjet.isEmpty()) {
                    competenceTrouvee = true;
                    System.out.println(projet.getId()+ "Competence matche");
                }

                System.out.println(projet.getNiveau().name() + " <- projet | client -> " +client.getNiveau().name());
                if (matchNiveau(projet.getNiveau().toString()) <= matchNiveau(client.getNiveau().name())) {
                    niveauCompatible = true;

                    System.out.println(projet.getId()+ "Niveau matche");
                }

                if (projet.getLocalite()==null || projet.getLocalite().getRegionClient().equals(client.getLocalite().getRegionClient())) {
                    localiteCompatible = true;
                    System.out.println(projet.getId()+ "Localite matche");
                }

                if (projet.getDomaine().getDomaine().equals(client.getDomaine().getDomaine())) {
                    domaineCompatible = true;
                    System.out.println(projet.getId()+ "Domaine matche");
                }

                if (competenceTrouvee && niveauCompatible && localiteCompatible && domaineCompatible) {
                    projetsRecommandes.add(projet);
                    System.out.println(projet.getTitre());
                }
            }
        }

        return projetsRecommandes;
    }
}