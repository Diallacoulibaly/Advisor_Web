package main.java.model.ServiceImplemente;

import java.util.List;
import java.util.Optional;

import main.java.model.dao.EtapeDao;
import main.java.model.service.EtapeService;
import main.java.model.classes.Etape;

//Service pour gérer les opérations liées aux étapes

public class EtapeServiceImplement implements EtapeService {

    private final EtapeDao etapeDao;

    public EtapeServiceImplement (EtapeDao etapeDao) {
        this.etapeDao = etapeDao;
    }

    //Ajouter une nouvelle étape
    @Override
    public boolean ajoutt(Etape etape) {
        if (etape == null) {
            System.out.println("L'étape ne peut pas être nulle.");
            return false;
        }
        if (etape.getTitre() == null || etape.getTitre().trim().isEmpty()) {
            System.out.println("Le titre de l'étape est obligatoire.");
            return false;
        }
        if (etape.getDescription() == null || etape.getDescription().trim().isEmpty()) {
            System.out.println("La description de l'étape est obligatoire.");
            return false;
        }
        if (etape.getStatutEtape() == null) {
            System.out.println("Le statut de l'étape est obligatoire.");
            return false;
        }

        etapeDao.ajout_etape(etape);
        System.out.println("Étape ajoutée avec succès.");
        return true;
    }

    //Rechercher une étape specifique
    @Override
    public Optional<Etape> etape(int idEtape) {
        if (idEtape <= 0) {
            System.out.println("L'ID de l'étape doit être un entier positif.");
            return Optional.empty();
        }
        return etapeDao.rech_etape(idEtape);
    }

    //Liste des étapes
    @Override
    public List<Etape> Les_etapes() {
        return etapeDao.Liste_etape();
    }

    //Mettre à jour une étape
    @Override
    public boolean miseAjour(Etape etape) {
        if ((etape == null) || (etape.getIdEtape() <= 0)) {
            System.out.println("L'ID de l'étape est obligatoire pour la mise à jour.");
            return false;
        }
        if (etape.getTitre() == null || etape.getTitre().trim().isEmpty()) {
            System.out.println("Le titre de l'étape est obligatoire.");
            return false;
        }

        boolean updated = etapeDao.mise_a_jour_etape(etape);
        System.out.println(updated ? "Étape mise à jour avec succès." : "Erreur lors de la mise à jour de l'étape.");
        return updated;
    }

    //Supprimer une étape
    @Override
    public boolean suppression(int idEtape) {
        if (idEtape <= 0) {
            System.out.println("L'ID de l'étape doit être un entier positif.");
            return false;
        }


        boolean deleted = etapeDao.suppr_etape(idEtape);
        System.out.println(deleted ? "Étape supprimée avec succès." : "Erreur lors de la suppression de l'étape.");
        return deleted;
    }


    @Override
    public List<Etape> ListeEtapesByProjet(int idProjet) {
        return etapeDao.getByProjetId(idProjet);
    }

    @Override
    public int countEtapes(int idProjet) {
        return etapeDao.count_etape(idProjet);
    }
}
