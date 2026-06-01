package main.java.model.classes;

public class ClientCompetence {

    private Integer idClient;
    private Integer idCompetence;



    // Constructros
    public ClientCompetence(Integer idClient, Integer idCompetence) {
        this.idClient = idClient;
        this.idCompetence = idCompetence;
    }



    // Getters et Setters
    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient() {
        this.idClient = idClient;
    }

    public Integer getIdCompetence() {
        return idCompetence;
    }

    public void SetidCompetence(){
        this.idCompetence = idCompetence;
    }

}
