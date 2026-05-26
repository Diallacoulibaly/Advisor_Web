package main.java.model.classes;


import main.java.model.enums.Satifaction;
import main.java.model.enums.StatutProjet;

import java.time.LocalDateTime;

public class ProjetClient {


    private int id;
    private Client client;
    private Projet projet;
    private LocalDateTime debut;
    private LocalDateTime fin;
    private StatutProjet statut;
    private Satifaction satisfaction;

    public ProjetClient() {
    }

    public ProjetClient(int id, Client client, Projet projet, LocalDateTime debut,
                        LocalDateTime fin, StatutProjet statut,
                        Satifaction satisfaction) {
        this.id = id;
        this.client = client;
        this.projet = projet;
        this.debut = debut;
        this.fin = fin;
        this.statut = statut;
        this.satisfaction = satisfaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public StatutProjet getStatut() {
        return statut;
    }

    public void setStatut(StatutProjet statut) {
        this.statut = statut;
    }

    public Satifaction getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Satifaction satisfaction) {
        this.satisfaction = satisfaction;
    }
}