package main.java.model.service;

import main.java.model.classes.Projet;

import java.util.List;
import java.util.Optional;

public interface ProjetService {
    Optional<Projet> getProjetById(int id);
    void updateProjet(Projet p);
    List<Projet> getAllProjets();
    void enregistrerProjet(Projet p);
    void supprimerProjet(int id);
}
