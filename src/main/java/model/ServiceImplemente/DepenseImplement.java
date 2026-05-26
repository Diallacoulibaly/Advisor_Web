/*package ServiceImplemente;

import dao.DepenseDao;
import Service.DepenseServiceDao;
import classes.Depense;

import java.util.List;
import java.util.Optional;

public class DepenseService implements DepenseServiceDao {
    private DepenseDao depenseDao;

    public DepenseService(DepenseDao depenseDao) {
        this.depenseDao = depenseDao;
    }

    @Override
    public void add(Depense depense) {
        if (depense.getMontant() <= 0){
            System.out.println("Le montant doit être supérieur à 0.");
            return;
        }
        depenseDao.add(depense);
    }

    @Override
    public Optional<Depense> getById(int id) {
        if (id < 0) {
            System.out.println("Le id-depense est obligatoire.");
        }
        depenseDao.getById(id);
        return Optional.empty();
    }

    @Override
    public List<Depense> getAll() {
        return depenseDao.getAll();
    }

    @Override
    public void update(int id) {
        if (id < 0) {
            System.out.println("Le id-depense est obligatoire.");
        }
        depenseDao.update(id);
    }

    @Override
    public void delete(int id) {
        if (id < 0) {
            System.out.println("Le id-depense est obligatoire.");
            return;
        }
        depenseDao.delete(id);
    }
}*/