package main.java.model.dao;

import main.java.model.classes.ProjetClient;
import main.java.model.enums.StatutProjet;

import java.util.List;
import java.util.Optional;

public interface ProjetClientDAO {
    boolean save(ProjetClient projetclient);

    void changerStatut(int id, StatutProjet statutProjet);

    //void delete(int id);

    Optional<ProjetClient> getById(int id);

    List<ProjetClient> getAll();

    List<ProjetClient> getByClient(int idClient);

    Optional<ProjetClient> getProjetEnCours(int idClient);
    public double getProjetDepenseEnCours(int idClient);
    boolean hasProjetEnCours(int idClient);

}
