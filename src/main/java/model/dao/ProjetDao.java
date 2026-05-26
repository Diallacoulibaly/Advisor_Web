package main.java.model.dao;

import java.util.List;
import java.util.Optional;
import main.java.model.classes.Projet;

public interface ProjetDao {
    void add(Projet projet);

    Optional<Projet> getById(int id);
    List<Projet> getAll();
    void update(int id); // Note : En général, on passe l'objet entier "Projet projet" pour une mise à jour
    int delete(int id);
    boolean existsByTitre(String titre);
    List<Projet> getProjetsByClient(int clientId);
}