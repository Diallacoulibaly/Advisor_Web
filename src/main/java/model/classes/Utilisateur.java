package main.java.model.classes;



import main.java.model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {


    // les informations des de base concernant un user
    protected  Integer idUtilisateur;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;
    protected String telephone;
    protected Role role;

    protected static List<Utilisateur> utilisateurs = new ArrayList<>();

    // CONSTRUCTEUR PRINCIPAL : Initialise un nouvel utilisateur avec tous les champs.
    public Utilisateur(String nom, String prenom, String email, String motDePasse, String telephone, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.role = role;

    }

    public Utilisateur(Integer id, String nom, String prenom, String email, String motDePasse, String telephone, Role role) {
        this.idUtilisateur = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.role = role;

    }

    public Utilisateur() {

    }
    // les getters

    // GETTERS : Méthodes pour récupérer les valeurs des attributs privés.
    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public Role getRole() {
        return role;
    }

    // SETTERS : Méthodes pour modifier les valeurs des attributs privés.
    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Note : Dans une vraie application, ceci serait fait via le repository/base de données.
    public void supprimerUtilisateur() {
        utilisateurs.remove(this);
    }

    // CONNEXION : Vérifie si les identifiants fournis correspondent à cet utilisateur.
    // Retourne true si l'email et le mot de passe correspondent.
    public boolean seConnecter(String email, String motDePasse) {
        return this.email.equals(email) && this.motDePasse.equals(motDePasse);
    }

    // MODIFIER : Met à jour les informations personnelles de l'utilisateur.
    public void modifierUtilisateur(String nom, String prenom, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    // AFFICHER : Affiche les détails de l'utilisateur dans la console.
    // Utile pour le débogage ou l'affichage simple.
    public void afficherUtilisateur() {
        System.out.println("ID: " + idUtilisateur);
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Email: " + email);
        System.out.println("Téléphone: " + telephone);
        System.out.println("Rôle: " + role);
        System.out.println("----------------------");
    }}





