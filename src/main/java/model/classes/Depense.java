package main.java.model.classes;

import java.sql.Date;

public class Depense {

    private Integer idDepense;
    private double montant;
    private String description;
    private Date date;
    private Activite activite;
    private Client client;

    public Depense(Integer idDepense, double montant, String description, Date date, Activite activite,Client client) {
        this.idDepense = idDepense;
        this.montant = montant;
        this.description = description;
        this.date = date;
        this.activite = activite;
        this.client=client;
    }

    // CORRECTION: ajout d'un constructeur sans Activite
    public Depense(Integer idDepense, double montant, String description, Date date) {
        this.idDepense = idDepense;
        this.montant = montant;
        this.description = description;
        this.date = date;
    }

    public Depense() {}

    public String getDepense(){return this.idDepense.toString();}

    public Integer getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(Integer idDepense) {  // CORRECTION: ajout du setter
        this.idDepense = idDepense;
    }

    public double getMontant() {
        return montant;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Activite getActivite() {
        return activite;
    }
    public Client getClient(){return  client;}

    public void setMontant(double montant) {
        if (montant >= 0) {
            this.montant = montant;
        } else {
            System.out.println("montant negatif");
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    public void setClient(Client client){this.client=client;}

    public void setDate(Date date) {
        this.date = date;
    }
}
