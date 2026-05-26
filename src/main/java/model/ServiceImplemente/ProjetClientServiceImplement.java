package main.java.model.ServiceImplemente;


import main.java.model.DaoImplement.ProjetClientDAOImplement;
import main.java.model.classes.ProjetClient;
import main.java.model.dao.ProjetClientDAO;
import main.java.model.enums.StatutProjet;
import main.java.model.service.ProjetClientService;

import java.util.List;
import java.util.Optional;

public class ProjetClientServiceImplement implements ProjetClientService {

    private ProjetClientDAO projetClientRepository= new ProjetClientDAOImplement();


    public ProjetClientServiceImplement(ProjetClientDAOImplement clientProjetTable){
        this.projetClientRepository= clientProjetTable;
    }



    @Override
    public void add(ProjetClient projetClient) {
        projetClientRepository.save(projetClient);
    }

    @Override
    public void changerStatut(int id, StatutProjet statutProjet) {
        projetClientRepository.changerStatut(id, statutProjet);
    }

    @Override
    public List<ProjetClient> getAll() {
        return projetClientRepository.getAll();
    }


    @Override
    public List<ProjetClient> getByClient(int idClient) {
        return projetClientRepository.getByClient(idClient);
    }

    @Override
    public Optional<ProjetClient> getById(int id) {
        return projetClientRepository.getById(id);
    }
}
