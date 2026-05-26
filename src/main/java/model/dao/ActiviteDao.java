package main.java.model.dao;
import main.java.model.classes.*;
import java.util.List;

public interface ActiviteDao {
    void ajouterActivite(Activite activite);
    List<Activite> afficherActivite();
    void modifierActivite(Activite activite);
    void marquerTerminer(int id);
    void supprimerActivite(int id);

}
