package main.java.model.service;


import main.java.model.classes.ProjetClient;
import main.java.model.enums.StatutProjet;

import java.util.List;
import java.util.Optional;

public interface ProjetClientService {
    void add(ProjetClient projetClient);
    void changerStatut(int id, StatutProjet statutProjet);
    Optional<ProjetClient> getById(int id);
    List<ProjetClient> getAll();
    List<ProjetClient> getByClient(int idClient);
}
