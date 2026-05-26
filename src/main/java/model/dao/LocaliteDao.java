package main.java.model.dao;

import java.util.List;

import main.java.model.classes.Localite;

public interface LocaliteDao {
    void add(Localite localite);
        Localite getById(int id);
        List<Localite> getAll();
        void update(int id, String regionClient);
        void delete(int id);
}
