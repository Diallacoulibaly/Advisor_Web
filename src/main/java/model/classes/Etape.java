package main.java.model.classes;

import main.java.model.enums.StatutEtape;
import main.java.model.classes.Projet;

public class Etape {
    private int idEtape;
    private String titre;
    private String description;
    private int ordre;
    private StatutEtape statutEtape;
    private Projet projet;

    //Le constructeurs
    public Etape() {}


    public Etape(int idEtape, String titre, String description, int ordre,StatutEtape statutEtape, Projet projet) {
        this.idEtape = idEtape;
        this.titre = titre;
        this.description = description;
        this.ordre = ordre;
        this.statutEtape = statutEtape;
        this.projet = projet;
    }


    // Getters et setters
    public int getIdEtape(){
        return idEtape;
    }
    public void setIdEtape(int idEtape) {
        this.idEtape = idEtape;
    }

    public String getTitre(){
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public int getOrdre(){return ordre;}
    public void setOrdre(int ordre){this.ordre = ordre;}


    public StatutEtape getStatutEtape(){
        return statutEtape;
    }
    public void  setStatutEtape(StatutEtape statutEtape){
        this.statutEtape = statutEtape;
    }

    public Projet getProjet(){
        return projet;
    }
    public void setProjet(Projet projet){
        this.projet = projet;
    }

    // Méthode pour valider et créer une étape
    @Override
    public String toString() {
        return "Etape{" +
                "idEtape=" + idEtape +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", ordre='" + ordre + '\'' +
                ", statut=" + statutEtape + '}';
    }
}

