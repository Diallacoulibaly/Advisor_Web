package main.java.model.service;

import main.java.model.classes.Domaine;

import java.util.List;

public interface DomaineService {
    public void ajouter(String domaine);
    public void modifier(int id , String domaine);
    public  void supprimer(int id);
    public List<Domaine> afficher();
}
