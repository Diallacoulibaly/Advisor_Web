package main.java.model.classes;

public class Competence {

    private int id;
    private String nom;

    public Competence() {}

    public Competence(String nom) {
        this.nom = nom;
    }

    public Competence(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
