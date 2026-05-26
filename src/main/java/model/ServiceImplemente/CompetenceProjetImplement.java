//package model.ServiceImplemente;
//
//import model.service.CompetenceProjetDao;
//import model.classes.CompetenceProjet;
//
//import java.util.List;
//import java.util.Optional;
//
//public class CompetenceProjetImplement implements CompetenceProjetDao {
//
//    private final CompetenceProjetDao competenceProjetDao;
//
//    public CompetenceProjetService(CompetenceProjetDao competenceProjetDao) {
//        this.competenceProjetDao = competenceProjetDao;
//    }
//
//    //Ajouter
//    @Override
//    public boolean ajouter(CompetenceProjet cp) {
//        if (cp == null) {
//            System.out.println("L'association Competence-Projet ne peut pas être nulle.");
//            return false;
//        }
//        if (cp.getCompetenceId() <= 0) {
//            System.out.println("L'ID de la compétence est obligatoire et doit être positif.");
//            return false;
//        }
//        if (cp.getIdProjet() <= 0) {
//            System.out.println("L'ID du projet est obligatoire et doit être positif.");
//            return false;
//        }
//
//        boolean result = competenceProjetDao.ajouter(cp);
//        System.out.println(result ? "Compétence associée au projet avec succès."
//                : "Échec de l'association de la compétence.");
//        return result;
//    }
//
//    //Rechercher
//    @Override
//    public Optional<CompetenceProjet> rechercher(int id) {
//        if (id <= 0) {
//            System.out.println("L'ID doit être un entier positif.");
//            return Optional.empty();
//        }
//        return competenceProjetDao.rechercher(id);
//    }
//
//    //Liste
//    @Override
//    public List<CompetenceProjet> lister() {
//        return competenceProjetDao.lister();
//    }
//
//    //Mise à jour
//    @Override
//    public boolean mettre_a_jour(CompetenceProjet cp) {
//        if (cp == null || cp.getCompetenceId() <= 0 || cp.getIdProjet() <= 0) {
//            System.out.println("Les IDs de compétence et de projet sont obligatoires pour la mise à jour.");
//            return false;
//        }
//
//        boolean updated = competenceProjetDao.mettre_a_jour(cp);
//        System.out.println(updated ? "Association mise à jour avec succès."
//                : "Échec de la mise à jour.");
//        return updated;
//    }
//
//    //Suppression
//    @Override
//    public boolean supprime(int id) {
//        if (id <= 0) {
//            System.out.println("L'ID doit être un entier positif.");
//            return false;
//        }
//
//        boolean deleted = competenceProjetDao.supprime(id);
//        System.out.println(deleted ? "Association supprimée avec succès."
//                : "Échec de la suppression.");
//        return deleted;
//    }
//
//    //Verification
//    @Override
//    public boolean verification(int id) {
//        return competenceProjetDao.verification(id);
//    }
//}