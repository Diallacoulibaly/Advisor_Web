package main.java.model.ServiceImplemente;

import main.java.model.dao.DepenseDao;
import main.java.model.classes.Depense;
import main.java.model.service.DepenseService;

import java.util.List;
import java.util.Optional;

public class DepenseImplement implements DepenseService {

    private DepenseDao depenseDao;

    public DepenseImplement(DepenseDao depenseDao) {
        this.depenseDao = depenseDao;
    }

    @Override
    public void add(Depense depense) {
        if (depense.getMontant() <= 0) {
            System.out.println("Le montant doit être supérieur à 0.");
            return;
        }
        depenseDao.add(depense);
    }

    @Override
    public Optional<Depense> getById(int id) {
        if (id <= 0) {  // CORRECTION: vérification plus stricte
            System.out.println("L'id de la dépense doit être positif.");
            return Optional.empty();
        }
        return depenseDao.getById(id);  // CORRECTION: retourner le résultat de la méthode
    }
    @Override
    public Optional<Depense> getByIdClient(int id) {
        if (id <= 0) {  // CORRECTION: vérification plus stricte
            System.out.println("L'id de la dépense doit être positif.");
            return Optional.empty();
        }
        return depenseDao.getByIdClient(id);  // CORRECTION: retourner le résultat de la méthode
    }


    @Override
    public List<Depense> getAll() {
        return depenseDao.getAll();
    }

    @Override
    public void update(Depense depense) {  // CORRECTION: reçoit un objet Depense
        if (depense == null || depense.getIdDepense() <= 0) {
            System.out.println("Dépense invalide ou id incorrect.");
            return;
        }
        depenseDao.update(depense);
    }

    @Override
    public void delete(int id) {
        if (id <= 0) {
            System.out.println("L'id de la dépense doit être positif.");
            return;
        }
        depenseDao.delete(id);
    }
}
