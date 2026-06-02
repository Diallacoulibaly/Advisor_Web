package main.java.model.dao;

import main.java.model.classes.Commentaire;
import main.java.model.classes.Domaine;

import java.util.List;

public interface CommentaireDao {
    public void ajoutCmt(Commentaire commentaire);
    List<Commentaire> afficherCmt();
    public void  modifierCmt(Commentaire commentaire);
    public void supprimerCmt(int id);
    public Commentaire getById(int id);
}
