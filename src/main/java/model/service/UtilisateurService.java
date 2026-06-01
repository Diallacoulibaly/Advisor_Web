package main.java.model.service;

import main.java.model.classes.Utilisateur;
import main.java.model.enums.Role;

import java.util.Optional;

public interface UtilisateurService {
    void addUtilisateur(Utilisateur utilisateur);

    Optional<Utilisateur> getUtilisateurById(int id);

    void getAllUtilisateurs();

    Optional<Utilisateur> getUtilisateurByEmail(String email);

    void getUtilisateursByRole(Role role);

    int authentifierUtilisateur(String email, String motDePasse);

    void updateUtilisateur(int id, String nom, String prenom, String email, Integer telephone);

    boolean changerMotDePasse(int id, String ancienMotDePasse, String nouveauMotDePasse);

    int deleteUtilisateur(int id);

    boolean utilisateurExistsByEmail(String email);

    void afficherDetailsUtilisateur(int id);

}
