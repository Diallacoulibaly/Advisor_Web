package main.java.model.ServiceImplemente;

import main.java.model.dao.CompetenceProjetDao;
import main.java.model.service.CompetenceProjetService;
import main.java.model.service.CompetenceService; // 1. Correction : Ajout du point-virgule
import main.java.model.classes.CompetenceProjet;

import java.util.List;

// 2. Correction : Implémente l'interface de Service et non du DAO
public class CompetenceProjetImplement implements CompetenceProjetService {

    private final CompetenceProjetDao competenceProjetDao;

    // 3. Correction : Le nom du constructeur correspond au nom de la classe
    public CompetenceProjetImplement(CompetenceProjetDao competenceProjetDao) {
        this.competenceProjetDao = competenceProjetDao;
    }

    // Ajouter
    public boolean ajouter(CompetenceProjet cp) { // Correspond à votre interface Service
        if (cp == null) {
            System.out.println("L'association Competence-Projet ne peut pas être nulle.");
            return false;
        }
        if (cp.getCompetenceId() <= 0 || cp.getIdProjet() <= 0) {
            System.out.println("Les IDs de compétence et de projet doivent être positifs.");
            return false;
        }

        boolean result = competenceProjetDao.add_CP(cp);
        System.out.println(result ? "Compétence associée avec succès." : "Échec de l'association.");
        return result;
    }

    public List<Integer> rechercher(int idProjet) {
        if (idProjet <= 0) {
            System.out.println("L'ID du projet doit être un entier positif.");
            return List.of();
        }
        return competenceProjetDao.rech_CP(idProjet);
    }

    // Liste
    public List<CompetenceProjet> lister() {
        return competenceProjetDao.ListeCP();
    }

    // Mise à jour
    public boolean mettre_a_jour(CompetenceProjet cp) {
        if (cp == null || cp.getCompetenceId() <= 0 || cp.getIdProjet() <= 0) {
            System.out.println("Les IDs de compétence et de projet sont obligatoires.");
            return false;
        }
        return competenceProjetDao.mise_a_jour_CP(cp);
    }

    public boolean supprimer(int idCompetence, int idProjet) {
        if (idCompetence <= 0 || idProjet <= 0) {
            System.out.println("Les identifiants doivent être positifs.");
            return false;
        }
        return competenceProjetDao.suppr_CP(idCompetence, idProjet);
    }

    // Verification
    public boolean verifier(int idCompetence) {
        return competenceProjetDao.verif_CP(idCompetence);
    }
}
