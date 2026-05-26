package main.java.model.service;

import java.util.List;

import main.java.model.classes.Localite;

public interface LocaliteService {
    void add(Localite localite);
        Localite getById(int id);
        List<Localite> getAll();
        void update(int id, String regionClient);
        void delete(int id);
}
