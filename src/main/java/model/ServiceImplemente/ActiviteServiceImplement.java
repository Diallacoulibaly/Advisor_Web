/*package main.java.model.ServiceImplemente;


import main.java.model.classes.Activite;
import main.java.model.dao.ActiviteDao;
import main.java.model.service.ActiviteService;

import java.util.List;

public class ActiviteServiceImplement implements ActiviteService {
    private ActiviteDao activiteDao;

    public ActiviteServiceImplement(ActiviteDao activiteDao) {
        this.activiteDao = activiteDao;
    }

    @Override
    public void ajouterActivite(Activite activite) {
        if (activite.getOrdre() <= 0 || activite.getDuree() <= 0) {
            System.out.println("L'ordre, la durée ou le montant de l'activité ne peuvent pas être négative !");
            return;
        }

        activiteDao.ajouterActivite(activite);

    }

    @Override
    public List<Activite> afficherActivite() {
        return activiteDao.afficherActivite();
    }

    @Override
    public void modifierActivite(Activite activite) {
        if (activite.getTitre() == null || activite.getTitre().trim().isEmpty()
                || activite.getDescription() == null || activite.getDescription().trim().isEmpty()) {
            System.out.println("Le titre ou la description ne peuvent pas être vide");
            return;
        } else if (activite.getOrdre() <= 0 || activite.getDuree() <= 0) {
            System.out.println("L'ordre, la durée ou le montant d'une activité ne peuvent pas être négatif !");
            return;

        } else {
            activiteDao.modifierActivite(activite);
            System.out.println("Activité modifiée avec succès");
        }
    }

    @Override
    public void marquerTerminer(int id) {
        if (id <= 0) {
            System.out.println("id de l'activité incorrecte ! ");
            return;
        }
        activiteDao.marquerTerminer(id);
    }

    @Override
    public void supprimerActivite(int id) {
        if (id <= 0) {
            System.out.println("id de l'activité incorrecte ! ");
            return;
        }

        activiteDao.supprimerActivite(id);
    }
}

 */