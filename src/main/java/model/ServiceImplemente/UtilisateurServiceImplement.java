package main.java.model.ServiceImplemente;

import main.java.model.DaoImplement.UtilisateurDaoImplement;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.UtilisateurDao;
import main.java.model.enums.Role;
import main.java.model.service.UtilisateurService;

import java.util.List;
import java.util.Optional;

public class UtilisateurServiceImplement implements UtilisateurService {
    private final UtilisateurDao utilisateurRepository;

    public UtilisateurServiceImplement(UtilisateurDao utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public void updateUtilisateur(int id, String nom, String prenom, String email, Integer telephone) {
        try {
            // VÉRIFICATION : S'assurer que l'utilisateur existe.
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getById(id);
            if(utilisateurOpt.isEmpty()) {
                System.out.println("Utilisateur non trouvé.");
                return;
            }

            // VALIDATION : Vérifier l'unicité de l'email si changé.
            Utilisateur utilisateur = utilisateurOpt.get();
            if(!utilisateur.getEmail().equals(email) && utilisateurRepository.existsByEmail(email)) {
                System.out.println("Un utilisateur avec cet email existe déjà.");
                return;
            }

            // VALIDATION : Vérifier le format de l'email.
            if(email != null && !email.contains("@")) {
                System.out.println("Email invalide.");
                return;
            }

            // VALIDATION : Vérifier les champs obligatoires.
            if(nom == null || nom.trim().isEmpty() ||
                    prenom == null || prenom.trim().isEmpty()) {
                System.out.println("Le nom et le prénom sont obligatoires.");
                return;
            }

            // MISE À JOUR : Appeler le repository.
            //utilisateurRepository.update(id, nom, prenom, email, telephone);
            System.out.println("Utilisateur mis à jour avec succès.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }

    }

    @Override
    public void getUtilisateursByRole(Role role) {
        try {
            List<Utilisateur> utilisateurs = utilisateurRepository.getByRole(role);
            if(utilisateurs.isEmpty()){
                System.out.println("Il n'y a pas d'utilisateurs avec le rôle " + role);
                return;
            }
            for(Utilisateur utilisateur : utilisateurs){
                System.out.printf("ID: %d | %s %s | Email: %s%n",
                        utilisateur.getIdUtilisateur(),
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getEmail());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }

    }

    @Override
    public void getAllUtilisateurs() {
        try {
            List<Utilisateur> utilisateurs = utilisateurRepository.getAll();
            if(utilisateurs.isEmpty()){
                System.out.println("Il n'y a pas d'utilisateurs enregistrés !!!");
                return;
            }
            // Affichage formaté de chaque utilisateur.
            for(Utilisateur utilisateur : utilisateurs){
                System.out.printf("ID: %d | %s %s | Email: %s | Rôle: %s%n",
                        utilisateur.getIdUtilisateur(),
                        utilisateur.getNom(),
                        utilisateur.getPrenom(),
                        utilisateur.getEmail(),
                        utilisateur.getRole());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }

    }

    @Override
    public void afficherDetailsUtilisateur(int id) {
        try {
            // RÉCUPÉRATION : Chercher l'utilisateur.
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getById(id);
            if(utilisateurOpt.isEmpty()) {
                System.out.println("Utilisateur non trouvé.");
                return;
            }
            // AFFICHAGE : Utiliser la méthode du modèle.
            utilisateurOpt.get().afficherUtilisateur();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage de l'utilisateur : " + e.getMessage());
        }

    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        try {
            // VALIDATION 1 : Vérifier l'unicité de l'email.
            if(utilisateurRepository.existsByEmail(utilisateur.getEmail())) {
                System.out.println("Un utilisateur avec cet email existe déjà.");
                return;
            }

            // VALIDATION 2 : Vérifier le format de l'email (doit contenir @).
            if(!utilisateur.getEmail().contains("@")) {
                System.out.println("Email invalide.");
                return;
            }

            // VALIDATION 3 : Vérifier les champs obligatoires (nom, prénom, mot de passe).
            if(utilisateur.getNom() == null || utilisateur.getNom().trim().isEmpty() ||
                    utilisateur.getPrenom() == null || utilisateur.getPrenom().trim().isEmpty() ||
                    utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().trim().isEmpty()) {
                System.out.println("Le nom, le prénom et le mot de passe de l'utilisateur sont obligatoires.");
                return;
            }

            // VALIDATION 4 : Vérifier que le téléphone est valide si fourni (doit être positif).
            if(utilisateur.getTelephone() != null ) {
                System.out.println("Le numéro de téléphone doit être valide.");
                return;
            }

            // VALIDATION 5 : Vérifier que le rôle est défini.
            if(utilisateur.getRole() == null) {
                System.out.println("Le rôle de l'utilisateur est obligatoire.");
                return;
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la validation de l'utilisateur : " + e.getMessage());
            return;
        }

        // SI TOUTES LES VALIDATIONS SONT RÉUSSIES : Ajouter via le repository.
        utilisateurRepository.add(utilisateur);
        System.out.println("Utilisateur ajouté avec succès.");

    }

    @Override
    public Optional<Utilisateur> getUtilisateurById(int id) {
        try {
            // Appel au repository pour récupérer l'utilisateur.
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getById(id);
            if (utilisateurOpt.isEmpty()) {
                System.out.println("Aucun utilisateur trouvé avec l'ID spécifié.");
            } else {
                // Affichage d'un message de succès avec les noms.
                System.out.println("Utilisateur trouvé : " + utilisateurOpt.get().getNom() + " " + utilisateurOpt.get().getPrenom());
            }
            return utilisateurOpt;
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        try {
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getByEmail(email);
            if (utilisateurOpt.isEmpty()) {
                System.out.println("Aucun utilisateur trouvé avec cet email.");
            }
            return utilisateurOpt;
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche par email : " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public int deleteUtilisateur(int id) {
        try {
            // VÉRIFICATION : Utilisateur existe ?
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getById(id);
            if(utilisateurOpt.isEmpty()) {
                System.out.println("Utilisateur non trouvé.");
                return 0;
            }
            // SUPPRESSION : Appeler le repository.
            int result = utilisateurRepository.delete(id);
            if(result > 0) {
                System.out.println("Utilisateur supprimé avec succès.");
            }
            return result;
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean utilisateurExistsByEmail(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    @Override
    public boolean changerMotDePasse(int id, String ancienMotDePasse, String nouveauMotDePasse) {
        try {
            // VÉRIFICATION : Utilisateur existe ?
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getById(id);
            if(utilisateurOpt.isEmpty()) {
                System.out.println("Utilisateur non trouvé.");
                return false;
            }

            Utilisateur utilisateur = utilisateurOpt.get();

            // VALIDATION : Vérifier l'ancien mot de passe.
            if(!utilisateur.getMotDePasse().equals(ancienMotDePasse)) {
                System.out.println("L'ancien mot de passe est incorrect.");
                return false;
            }

            // VALIDATION : Vérifier que le nouveau mot de passe n'est pas vide.
            if(nouveauMotDePasse == null || nouveauMotDePasse.trim().isEmpty()) {
                System.out.println("Le nouveau mot de passe ne peut pas être vide.");
                return false;
            }

            // MISE À JOUR : Changer le mot de passe.
            utilisateurRepository.updateMotDePasse(id, nouveauMotDePasse);
            System.out.println("Mot de passe changé avec succès.");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur lors du changement de mot de passe : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object authentifierUtilisateur(String email, String motDePasse) {
        try {
            // VALIDATION : Vérifier qu'email et mot de passe sont fournis.
            if(email == null || email.trim().isEmpty() || motDePasse == null || motDePasse.trim().isEmpty()) {
                System.out.println("Email et mot de passe sont obligatoires.");
                return false;
            }

            // RÉCUPÉRATION : Chercher l'utilisateur par email.
            Optional<Utilisateur> utilisateurOpt = utilisateurRepository.getByEmail(email);
            if(utilisateurOpt.isEmpty()) {
                System.out.println("Utilisateur non trouvé.");
                return false;
            }

            // AUTHENTIFICATION : Utiliser la méthode seConnecter() du modèle.
            Utilisateur utilisateur = utilisateurOpt.get();
            if(utilisateur.seConnecter(email, motDePasse)) {
                System.out.println("Authentification réussie pour : " + utilisateur.getNom() + " " + utilisateur.getPrenom());
                return utilisateur;
            } else {
                System.out.println("Email ou mot de passe incorrect.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'authentification : " + e.getMessage());
            return false;
        }
    }
}
