package model.dao;

import main.java.model.classes.Utilisateur;
import main.java.model.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UtilisateurDao {
    // AJOUTER : Insère un nouvel utilisateur dans la base de données.
    void add(Utilisateur utilisateur);

    // RÉCUPÉRER PAR ID : Retourne un utilisateur optionnel par son identifiant unique.
    // Utilise Optional pour gérer les cas où l'utilisateur n'existe pas.
    Optional<Utilisateur> getById(int id);

    // RÉCUPÉRER PAR EMAIL : Retourne un utilisateur optionnel par son email.
    Optional<Utilisateur> getByEmail(String email);

    // RÉCUPÉRER TOUS : Retourne la liste de tous les utilisateurs.
    List<Utilisateur> getAll();

    // RÉCUPÉRER PAR RÔLE : Retourne la liste des utilisateurs ayant un rôle spécifique.
    List<Utilisateur> getByRole(Role role);

    // METTRE À JOUR : Modifie les informations d'un utilisateur existant.
    void update(int id, String nom, String prenom, String email, String telephone);

    // METTRE À JOUR MOT DE PASSE : Change le mot de passe d'un utilisateur.
    void updateMotDePasse(int id, String nouveauMotDePasse);

    // SUPPRIMER : Supprime un utilisateur par son ID. Retourne le nombre de lignes affectées.
    int delete(int id);

    // EXISTE PAR EMAIL : Vérifie si un email est déjà utilisé. Retourne true si oui.
    boolean existsByEmail(String email);
}
