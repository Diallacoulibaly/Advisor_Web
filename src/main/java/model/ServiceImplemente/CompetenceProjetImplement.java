package main.java.model.ServiceImplemente;

import main.java.model.service.CompetenceProjetService;
import main.java.model.dao.CompetenceProjetDao;
import main.java.model.classes.CompetenceProjet;
import java.util.List;
import java.util.Optional;

public class CompetenceProjetImplement implements CompetenceProjetService {

    private final CompetenceProjetDao competenceProjetDao;

    public CompetenceProjetImplement(CompetenceProjetDao competenceProjetDao) {
        this.competenceProjetDao = competenceProjetDao;
    }

    // Ajouter
    @Override
    public boolean ajouter(CompetenceProjet cp) {
        if (cp == null) {
            System.out.println("L'association Competence-Projet ne peut pas être nulle.");
            return false;
        }
        if (cp.getCompetenceId() <= 0) {
            System.out.println("L'ID de la compétence est obligatoire et doit être positif.");
            return false;
        }
        if (cp.getIdProjet() <= 0) {
            System.out.println("L'ID du projet est obligatoire et doit être positif.");
            return false;
        }

        try {
            // Correction : add_CP retourne void. On l'appelle directement.
            competenceProjetDao.add_CP(cp);
            System.out.println("Compétence associée au projet avec succès.");
            return true;
        } catch (Exception e) {
            System.out.println("Échec de l'association de la compétence : " + e.getMessage());
            return false;
        }
    }

    // Rechercher
    @Override
    public List<Integer> rechercher(int idProjet) {
        if (idProjet <= 0) {
            System.out.println("L'ID du projet doit être un entier positif.");
            return List.of(); // Retourne une liste vide
        }
        return competenceProjetDao.rech_CP(idProjet);
    }

    // Liste
    @Override
    public List<CompetenceProjet> lister() {
        return competenceProjetDao.ListeCP();
    }

    // Mise à jour
    @Override
    public boolean mettre_a_jour(CompetenceProjet cp) {
        if (cp == null || cp.getCompetenceId() <= 0 || cp.getIdProjet() <= 0) {
            System.out.println("Les IDs de compétence et de projet sont obligatoires pour la mise à jour.");
            return false;
        }

        boolean updated = competenceProjetDao.mise_a_jour_CP(cp);
        System.out.println(updated ? "Association mise à jour avec succès."
                : "Échec de la mise à jour.");
        return updated;
    }

    // Suppression
    @Override
    public boolean supprimer(int id) {
        if (id <= 0) {
            System.out.println("L'ID doit être un entier positif.");
            return false;
        }

        boolean deleted = competenceProjetDao.suppr_CP(id);
        System.out.println(deleted ? "Association supprimée avec succès."
                : "Échec de la suppression.");
        return deleted;
    }

    // Verification
    @Override
    public boolean verifier(int id) {
        return competenceProjetDao.verif_CP(id);
    }
}
