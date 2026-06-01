package main.java.model.dao;

import java.util.List;
import java.util.Optional;
import main.java.model.classes.Projet;

public interface ProjetDao {
    void add(Projet projet);
    Optional<Projet> getById(int id);
    List<Projet> getAll();
    void update(Projet projet);
    void delete(int id);
    boolean existsByTitre(String titre);
}