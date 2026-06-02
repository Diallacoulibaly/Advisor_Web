package main.java.model.ServiceImplemente;

import main.java.model.classes.ClientCompetence;
import main.java.model.dao.ClientCompetenceDao;
import main.java.model.service.ClientCompetenceService;

import java.sql.SQLException;
import java.util.List;

public class ClientCompetenceServiceImplement implements ClientCompetenceService {



    private final ClientCompetenceDao clientCompetenceRepository;

    public ClientCompetenceServiceImplement(ClientCompetenceDao clientCompetenceRepository) {
        this.clientCompetenceRepository = clientCompetenceRepository;
    }

    @Override
    public void add(ClientCompetence clientCompetence) {

        if (clientCompetence.getIdCompetence() == null || clientCompetence.getIdClient()==null ) {
            System.out.println("Erreur : objet ClientCompetence null");
            return;
        }
        clientCompetenceRepository.add(clientCompetence);
    }

    @Override
    public List<ClientCompetence> getAll() {
        return clientCompetenceRepository.getAll();
    }

    @Override
    public void update(ClientCompetence cc, int oldIdClient, int oldIdCompetence) {

        if (cc == null) {
            System.out.println("Erreur : aucune donnée ClientCompetence fournie");
            return;
        }

        clientCompetenceRepository.update(cc, oldIdClient, oldIdCompetence);
    }

    @Override
    public void delete(int idClient, int idCompetence) {

        if (idClient <= 0 || idCompetence <= 0) {
            System.out.println("ID invalide");
            return;
        }

        clientCompetenceRepository.delete(idClient, idCompetence);
    }

    @Override
    public List<Integer> filterSkills(List<Integer> competences, int id) {
        try{
            List<Integer> exitsSkills= clientCompetenceRepository.getSkillsByClient(id);
            if(exitsSkills.isEmpty()){
                return competences;
            }
            exitsSkills.forEach(competences::remove);
            return  competences;
        } catch (SQLException e){
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public void addListClientCompetence(List<Integer> competencesId, int id) {
        try {
            competencesId.forEach(idC -> {
                ClientCompetence clientCompetence= new ClientCompetence(id, idC);
                clientCompetenceRepository.add(clientCompetence);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

