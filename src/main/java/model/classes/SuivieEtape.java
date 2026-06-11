package main.java.model.classes;

import main.java.model.enums.StatutEtape;

public class SuivieEtape {
    private int id;
    private Etape etape;
    private Client client;
    private StatutEtape statutEtape;
    public SuivieEtape(){

    }
    public  SuivieEtape(int id,Etape etape,Client client,StatutEtape statutEtape){
        this.id=id;
        this.etape=etape;
        this.client=client;
        this.statutEtape=statutEtape;
    }
    public int getId(){
        return id;
    }
    public Etape getEtape(){
        return etape;
    }
    public Client getClient(){
        return client;
    }
    public StatutEtape getStatutEtape(){
        return statutEtape;
    }
    //setter
    public void setId(int id){
        this.id=id;
    }
    public void setEtape(Etape etape){
        this.etape=etape;
    }
    public void  setClient(Client client){
        this.client=client;
    }
    public void setStatutEtape(StatutEtape statutEtape){
        this.statutEtape=statutEtape;
    }
}