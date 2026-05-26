package main.java.model.classes;

public class CompetenceProjet {
    private int competenceId;
    private  int projetId;

    //Constructeurs
    public CompetenceProjet(){};
    public CompetenceProjet(int competenceId, int projetId){
        this.competenceId = competenceId;
        this.projetId = projetId;
    }

    //Getters setters

    public int getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(int competenceId) {
        this.competenceId = competenceId;
    }

    public int getIdProjet() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }


}
