package main.java.model.dao;
import main.java.model.classes.Localite;

import java.util.List;

public interface LocaliteDao {
    void add(Localite localite);
        Localite getById(int id);
        List<Localite> getAll();
        void update(int id, String regionClient);
        void delete(int id);
}
