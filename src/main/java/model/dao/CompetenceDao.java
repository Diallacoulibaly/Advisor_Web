package main.java.model.dao;

import main.java.model.classes.Competence;

import java.util.List;

public interface CompetenceDao {
    void ajouterCompetence(Competence competence);
    List<Competence> afficherCompetences();
    void modifierCompetence(Competence competence);
    void supprimerCompetence(int id);
    Competence getById(int id);
}
