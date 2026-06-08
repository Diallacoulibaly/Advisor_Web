package main.java.model.classes;

import main.java.model.enums.Statut;

public class ActiviteClient {
    private int idClient;
    private int idActivite;
    private Statut statut;

    public ActiviteClient(){

    }
    public ActiviteClient(int idClient, int idActivite, Statut statut){
        this.idClient = idClient;
        this.idActivite = idActivite;
        this.statut = statut;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdActivite() {
        return idActivite;
    }

    public Statut getStatut() {
        return statut;
    }
    //Les setters

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdActivite(int idActivite) {
        this.idActivite = idActivite;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
