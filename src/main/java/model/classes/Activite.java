package main.java.model.classes;
import main.java.model.enums.*;

//Implémentation de l'objet activité avec ses attributs
public class Activite {
    private int id;
    private String titre;
    private String description;
    private int ordre;
    private int duree;
    private int montant_activite;
    private Etape etape;

    // Constructeur vide
    public Activite() {}

    // Contruteurs avec paramètres
    public Activite(int id, String titre, String description, int ordre, int duree, Etape etape){
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.ordre = ordre;
        this.duree = duree;
        this.montant_activite = montant_activite;
        this.etape = etape;
    }

    // Les getters (permettent de récuperer une information id, titre, description etc
    public int getId(){
        return id;
    }

    public String getTitre(){
        return titre;
    }

    public String getDescription(){
        return description;
    }

    public int getOrdre(){
        return ordre;
    }

    public int getDuree(){
        return duree;
    }

    /*public int getMontantActivite(){
        return montant_activite;
    }*/


    public Etape getEtape(){
        return etape;
    }

    // Les setters, ils permettrons de modifier des informations de l'objet comme le titre, la description etc
    public void setTitre(String titre){
        this.titre = titre;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setOrdre(int ordre){
        this.ordre = ordre;
    }

    public void setDuree(int duree){
        this.duree = duree;
    }

    /*public void setMontantActivite(int montant_activite){
        this.montant_activite = montant_activite;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setEtape(Etape etape){
        this.etape = etape;
    }
}
