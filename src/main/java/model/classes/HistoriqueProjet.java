package main.java.model.classes;

public class HistoriqueProjet {

    private Integer idProjet;
    private Integer idHistorique;



    public HistoriqueProjet() {}

    public HistoriqueProjet(Integer idHistorique, Integer idProjet) {
        this.idProjet = idProjet;
        this.idHistorique = idHistorique;
    }



    // Getters et Setters
    public Integer getIdClient() {
        return idProjet;
    }

    public void setIdClient() {
        this.idProjet = idProjet;
    }

    public Integer getIdCompetence() {
        return idHistorique;
    }

    public void SetIdHistorique(int id){
        this.idHistorique = id;
    }

}
