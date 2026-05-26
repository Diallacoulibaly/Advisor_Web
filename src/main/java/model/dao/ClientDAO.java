package main.java.model.dao;

import main.java.model.classes.Client;
import main.java.model.classes.Domaine;
import main.java.model.classes.Localite;
import main.java.model.classes.Utilisateur;
import main.java.model.enums.Niveau;

import java.util.List;
import java.util.Optional;

public interface ClientDAO {
    boolean add(Client client);
    boolean addInfoClient(int clientID, Niveau niveau, Localite localite, Domaine domaine, int budget);
    Optional<Client> getById(int id);
    List<Client> getAll();
    boolean update(int id, String nom, String prenom, String telephone, Niveau niveau , int idlocalite, int iddomaine, int budget);
    boolean delete(int id);
    boolean existsByEmail(String email);
}
