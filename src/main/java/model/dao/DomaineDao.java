package main.java.model.dao;

import main.java.model.classes.Domaine;

import java.util.List;

public interface DomaineDao {
    public void ajoutDomaine(Domaine domaine);
    List<Domaine> afficherDomaine();
    public void  modifierDomaine(Domaine domaine);
    public void supprimerDomaine(int id);
}
