package main.java.model.dao;
import main.java.model.classes.Activite;

import java.util.List;

public interface ActiviteDao {
    void ajouterActivite(Activite activite);
    List<Activite> afficherActivite();
    void modifierActivite(Activite activite);
    void marqueTerminer(int id);
    void supprimerActivite(int id);

}
