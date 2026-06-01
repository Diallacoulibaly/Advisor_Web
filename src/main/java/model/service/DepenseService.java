package main.java.model.service;

import main.java.model.classes.Depense;
import java.util.List;
import java.util.Optional;

public interface DepenseService {

    void add(Depense depense);
    Optional<Depense> getById(int id);
    List<Depense> getAll();
    void update(Depense depense);  // CORRECTION: reçoit un objet Depense
    void delete(int id);
}
