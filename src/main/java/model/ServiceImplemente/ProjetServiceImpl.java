package main.java.model.ServiceImplemente;


import main.java.model.classes.Projet;
import main.java.model.dao.ProjetDao;
import main.java.model.service.ProjetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




public class ProjetServiceImpl implements ProjetService {
    private final ProjetDao projetDao;

    public ProjetServiceImpl(ProjetDao projetDao) {
        this.projetDao = projetDao;
    }

    public Optional<Projet> getProjetById(int id) {
        return projetDao.getById(id);
    }

    public void updateProjet(Projet p) {
        projetDao.update(p);
    }

    public List<Projet> getAllProjets() { return projetDao.getAll(); }

    public void enregistrerProjet(Projet projet) {
        try {
            if(projetDao.existsByTitre(projet.getTitre())) {
                throw new IllegalArgumentException("Un projet avec ce titre existe déjà.");
            }
            if(projet.getTitre() == null || projet.getTitre().trim().isEmpty() ||
                    projet.getDescription() == null || projet.getDescription().trim().isEmpty()) {
                throw new IllegalArgumentException("Le titre et la description du projet sont obligatoires.");
            }
            if(projet.getDuree() <= 0) {
                throw new IllegalArgumentException("La durée du projet doit être supérieure à 0.");
            }
            if(projet.getBudgetMin() < 0 || projet.getBudgetMax() < 0) {
                throw new IllegalArgumentException("Les budgets du projet ne peuvent pas être négatifs.");
            }
            if(projet.getBudgetMax() < projet.getBudgetMin()) {
                throw new IllegalArgumentException("Le budget maximum ne peut pas être inférieur au budget minimum.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur lors de la validation du projet : " + e.getMessage());
        }

        projetDao.add(projet);
        System.out.println("Projet ajouté avec succès.");
    }

    public void supprimerProjet(int id) {
        projetDao.delete(id);
    }

    public boolean projetExistsByTitre(String titre) {
        return projetDao.existsByTitre(titre);
    }

}