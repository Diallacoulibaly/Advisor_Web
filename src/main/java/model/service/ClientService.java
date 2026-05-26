package main.java.model.service;

import main.java.model.classes.Client;
import main.java.model.classes.Domaine;
import main.java.model.classes.Localite;
import main.java.model.enums.Niveau;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    boolean addClient(Client client);
    boolean addInfoClient(int clientID, Niveau niveau, Localite localite, Domaine domaine, int budget);
    Optional<Client> getClientById(int id);
    List<Client> getAllClients();
    boolean updateClient(int id, String nom, String prenom, String telephone, Niveau niveau , int idlocalite,  int iddomaine, int budget);
    boolean deleteClient(int id);
    boolean clientExistsByEmail(String email);
}
