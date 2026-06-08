package main.java.model.classes;

public class HistoriqueProjet {

    private Projet projet;
    private Historique historique;



    public HistoriqueProjet() {}

    public HistoriqueProjet(Historique historique, Projet projet) {
        this.projet = projet;
        this.historique = historique;
    }



    // Getters et Setters

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setHistorique(Historique historique) {
        this.historique = historique;
    }

    public Historique getHistorique() {
        return historique;
    }



}
