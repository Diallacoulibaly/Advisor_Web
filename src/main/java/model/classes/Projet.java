package main.java.model.classes;


import main.java.model.enums.StatutProjet;

public class Projet {

    private int id;
    private String titre;
    private String description;
    private float duree;
    private double budgetMin;
    private double budgetMax;
    private StatutProjet projetStatut;

    // 2. Attributs d'Association
//    private List<Etape> etapes;            // Relation "Contenir" (1..*)
//    private List<Commentaire> commentaires; // Relation "Concerner" (1..*)
//    private List<ProjetClient> realisations; // Relation "Realiser" (1..*)

    // 3. Constructeur complet
    public Projet(int id, String titre, String description, float duree, double budgetMin, double budgetMax) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;

        // Initialisation des listes pour éviter les NullPointerException
//        this.etapes = new ArrayList<>();
//        this.commentaires = new ArrayList<>();
//        this.realisations = new ArrayList<>();
    }

    public Projet(){}

    // 4. Vos Méthodes Métiers (UML)
    public void ajouterProjet() {
        // Logique métier pour enregistrer le projet
        System.out.println("Projet \"" + this.titre + "\" ajouté au modèle.");
    }

    public void afficherProjet() {
        System.out.println("Projet ID: " + this.id + " | Titre: " + this.titre);
        System.out.println("Description: " + this.description);
//        System.out.println("Nombre d'étapes associées : " + this.etapes.size());
    }

    public void modifierProjet(String titre, String description, float duree, double budgetMin, double budgetMax) {
        this.titre = titre;
        this.description = description;
        this.duree = duree;
        this.budgetMin = budgetMin;
        this.budgetMax = budgetMax;
    }

//    public void supprimerProjet() {
//        // Logique de suppression (et nettoyage des associations si nécessaire)
//        this.etapes.clear();
//        this.commentaires.clear();
//        System.out.println("Le projet ID " + this.id + " a été supprimé.");
//    }

    // 5. Getters et Setters pour les attributs et les relations
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public float getDuree() { return duree; }
    public void setDuree(float duree) { this.duree = duree; }

    public StatutProjet getProjetStatut(){ return projetStatut; }

    public void setProjetStatut(StatutProjet statutProjet){
        this.projetStatut = projetStatut;
    }

    public double getBudgetMin() { return budgetMin; }
    public void setBudgetMin(double budgetMin) { this.budgetMin = budgetMin; }

    public double getBudgetMax() { return budgetMax; }
    public void setBudgetMax(double budgetMax) { this.budgetMax = budgetMax; }

    // Getters et Setters pour manipuler les associations
//    public List<Etape> getEtapes() { return etapes; }
//    public void setEtapes(List<Etape> etapes) { this.etapes = etapes; }
//
//    public List<Commentaire> getCommentaires() { return commentaires; }
//    public void setCommentaires(List<Commentaire> commentaires) { this.commentaires = commentaires; }
//
//    public List<ProjetClient> getRealisations() { return realisations; }
//    public void setRealisations(List<ProjetClient> realisations) { this.realisations = realisations; }


}
