package main.java.model.service;

import main.java.model.classes.Commentaire;

import java.util.List;

public interface CommentaireService {
    public void ajouter(String commentaire);
    public void modifier(int id , String commentaire);
    public  void supprimer(int id);
    public List<Commentaire> afficher();
    public Commentaire getById(int id);
}
