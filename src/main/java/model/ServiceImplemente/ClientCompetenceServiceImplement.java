/*package ServiceImplementation;

import DAO.ClientCompetenceRepository;
import Service.ClientCompetenceInterface;
package ServiceImplementation;

import DAO.ClientCompetenceRepository;
import Service.ClientCompetenceInterface;
import Models.ClientCompetence;

import java.util.List;

public class ClientCompetenceServiceImplement {



    private final ClientCompetenceRepository clientCompetenceRepository;

    public ClientCompetenceService(ClientCompetenceRepository clientCompetenceRepository) {
        this.clientCompetenceRepository = clientCompetenceRepository;
    }

    public void add(ClientCompetence clientCompetence) {

        if (clientCompetence.getCompetence() == null || clientCompetence.getClient()==null ) {
            System.out.println("Erreur : objet ClientCompetence null");
            return;
        }
        clientCompetenceRepository.add(clientCompetence);
    }

    public List<ClientCompetence> getAll() {
        return clientCompetenceRepository.getAll();
    }

    public void update(ClientCompetence clientCompetence) {

        if (clientCompetence == null) {
            System.out.println("Erreur : aucune donnée ClientCompetence fournie");
            return;
        }

        clientCompetenceRepository.update(clientCompetence);
    }

    public void delete(int id) {

        if (id <= 0) {
            System.out.println("ID invalide");
            return;
        }

        clientCompetenceRepository.delete(id);
    }
}
*/