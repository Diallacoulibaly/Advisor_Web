package main.java.model.service;

import main.java.model.classes.Competence;

import java.util.List;

public interface CompetenceService {
    void ajouter(String nom);
    void modifier(int id, String nom);
    void supprimer(int id);
    List<Competence> afficher();
    Competence getById(int id);
}
