package main.java.model.dao;

import java.util.List;
import java.util.Optional;
import main.java.model.classes.Depense;

public interface DepenseDao {

    void add(Depense depense);
    Optional<Depense> getById(int id);
    List<Depense> getAll();
    void update(Depense depense);  // CORRECTION: reçoit un objet Depense, pas un int
    void delete(int id);
}
