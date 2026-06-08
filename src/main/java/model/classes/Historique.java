package main.java.model.classes;

import java.time.LocalDate;

public class Historique {

    int id;
    LocalDate date;
    int idClient;
    String descriptionAction;

    public Historique() {
    }

    public Historique(int id, LocalDate date, int idClient, String descriptionAction ) {
        this.id = id;
        this.date = date;
        this.idClient = idClient;
        this.descriptionAction = descriptionAction;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getIdClient(){
        return idClient;
    }


    public String getDescriptionAction(){return descriptionAction; }

    public void setId(int id){
        this.id = id;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    public void setDescriptionAction(String descriptionAction ) {this.descriptionAction = descriptionAction;}

}