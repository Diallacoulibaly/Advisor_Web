package main.java.model.ServiceImplemente;

import main.java.model.DaoImplement.ClientDAOImplement;
import main.java.model.classes.Client;
import main.java.model.classes.Localite;
import main.java.model.dao.ClientDAO;
import main.java.model.enums.Niveau;
import main.java.model.service.ClientService;

import java.util.List;
import java.util.Optional;

public class ClientServiceImplement implements ClientService {

    private ClientDAO clientRepository=new ClientDAOImplement();
    

    @Override
    public boolean addClient(Client client) {
        try {
            if(clientRepository.existsByEmail(client.getEmail())) {
                System.out.println("Un client avec cet email existe déjà.");
                return false;
            }
            else if(client.getEmail() != null && !client.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Email invalide.");
                return false;
            }
            else if(client.getNom() == null || client.getNom().trim().isEmpty() || client.getPrenom() == null || client.getPrenom().trim().isEmpty() || client.getTelephone() == null || client.getMotDePasse()==null || client.getMotDePasse().trim().isEmpty() ) {
                System.out.println("Le nom, le prénom, le téléphone et le mot de passe du client sont obligatoires.");
                return false;
            }
            return clientRepository.add(client);
        } catch (Exception e) {
            System.out.println("Erreur lors de la validation du client : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean addInfoClient(int clientID, Niveau niveau, Localite localite, int budget) {
        try {
            if(clientID==0 || niveau==null || niveau.name().isEmpty() || localite==null || budget<=0) {
                return false;
            }
        } catch (IllegalArgumentException e){
            e.printStackTrace(System.out);
        }
        return clientRepository.addInfoClient(clientID, niveau, localite, budget);

    }

    @Override
    public Optional<Client> getClientById(int id) {
        return clientRepository.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return  clientRepository.getAll();
    }

    @Override
    public boolean updateClient(int id, String nom, String prenom, String telephone, Niveau niveau , int idlocalite, int budget) {
        if(nom.isEmpty() ||  prenom.isEmpty()  ||  telephone.isEmpty() ||  niveau.name().isEmpty() ){
            System.out.println("Remplissez correctement les champs!!!");
            return false;
        }
        if(budget<100000){
            System.out.println("Le budget ne pas peut être inferieur a 100.000 F");
        }
        return clientRepository.update(id, nom, prenom, telephone, niveau, idlocalite, budget);
    }

    @Override
    public boolean deleteClient(int id) {
        return clientRepository.delete(id);
    }

    @Override
    public boolean clientExistsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

}
