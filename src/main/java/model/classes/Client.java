package main.java.model.classes;

import main.java.model.enums.Niveau;
import main.java.model.enums.Role;

public class Client extends Utilisateur {
    private Niveau niveau = Niveau.DEBUTANT;
    private Localite localite;
    private int  budgetApporte;
    private Domaine domaine;

    public Client() {

    }

    public Client(String nom, String prenom, String email, String motDePasse, Niveau niveau,
                  Localite localite, String telephone, Role role) {
        super(nom, prenom, email, motDePasse, telephone, role);
        this.niveau = niveau;

        this.localite = localite;

    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    public int getBudgetApporte() {
        return budgetApporte;
    }

    public void setBudgetApporte(int budget) {
        this.budgetApporte = budget;
    }



    public Localite getLocalite() {
        return localite;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

}
