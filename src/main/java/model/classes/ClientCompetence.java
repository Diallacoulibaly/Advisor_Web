package Models;

public class ClientCompetence {

    private Integer id;
    private Competence competence;
    private Client client;


    // Constructros
    public ClientCompetence(Integer id, Competence competence, Client client) {
        this.id = id;
        this.competence = competence;
        this.client = client;
    }

    public ClientCompetence(){

    }


    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
