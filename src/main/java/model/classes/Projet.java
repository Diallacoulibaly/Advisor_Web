package model.classes;

import model.enums.Niveau;
import model.enums.StatutProjet;

public class Projet {

    private int id;
    private String titre;
    private String description;
    private float duree;
    private Niveau niveau;
    private double budgetMin;
    private double budgetMax;
    private StatutProjet projetStatut;

    /*
    private List<Etape> etapes;            // Relation "Contenir" (1..*)
    private List<Commentaire> commentaires; // Relation "Concerner" (1..*)
    private List<ProjetClient> realisations; // Relation "Realiser" (1..*)
    */

    public Projet(int id, String titre, String description, float duree, double budgetMin, double budgetMax) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;

        /*
        this.etapes = new ArrayList<>();
        this.commentaires = new ArrayList<>();
        this.realisations = new ArrayList<>();
         */
    }

    public Projet(){}


    // Getters et Setters pour manipuler les associations
    /*
    public List<Etape> getEtapes() { return etapes; }
    public void setEtapes(List<Etape> etapes) { this.etapes = etapes; }

    public List<Commentaire> getCommentaires() { return commentaires; }
    public void setCommentaires(List<Commentaire> commentaires) { this.commentaires = commentaires; }

    public List<ProjetClient> getRealisations() { return realisations; }
    public void setRealisations(List<ProjetClient> realisations) { this.realisations = realisations; }

    */



    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return float return the duree
     */
    public float getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(float duree) {
        this.duree = duree;
    }

    /**
     * @return Niveau return the niveau
     */
    public Niveau getNiveau() {
        return niveau;
    }

    /**
     * @param niveau the niveau to set
     */
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    /**
     * @return double return the budgetMin
     */
    public double getBudgetMin() {
        return budgetMin;
    }

    /**
     * @param budgetMin the budgetMin to set
     */
    public void setBudgetMin(double budgetMin) {
        this.budgetMin = budgetMin;
    }

    /**
     * @return double return the budgetMax
     */
    public double getBudgetMax() {
        return budgetMax;
    }

    /**
     * @param budgetMax the budgetMax to set
     */
    public void setBudgetMax(double budgetMax) {
        this.budgetMax = budgetMax;
    }

    /**
     * @return StatutProjet return the projetStatut
     */
    public StatutProjet getProjetStatut() {
        return projetStatut;
    }

    /**
     * @param projetStatut the projetStatut to set
     */
    public void setProjetStatut(StatutProjet projetStatut) {
        this.projetStatut = projetStatut;
    }

}