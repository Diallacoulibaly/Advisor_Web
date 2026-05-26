package main.java.model.ServiceImplemente;


import main.java.model.classes.Localite;
import main.java.model.dao.LocaliteDao;
import main.java.model.service.LocaliteService;

import java.util.List;



public class LocaliteServiceImplemente implements LocaliteService {
    private final LocaliteDao localiteDao;

    public LocaliteServiceImplemente(LocaliteDao localiteDao) {
        this.localiteDao = localiteDao;
    }

    @Override
    public void add(Localite localite) {
        if (localite == null) {
            throw new IllegalArgumentException("La localité ne peut pas être nulle.");
        }
        if (localite.getRegionClient() == null || localite.getRegionClient().trim().isEmpty()) {
            throw new IllegalArgumentException("La région du client est obligatoire.");
        }
        localiteDao.add(localite);
    }

    @Override
    public Localite getById(int id) {
        return localiteDao.getById(id);
    }

    @Override
    public List<Localite> getAll() {
        return localiteDao.getAll();
    }

    @Override
    public void update(int id, String regionClient) {
        if (regionClient == null || regionClient.trim().isEmpty()) {
            throw new IllegalArgumentException("La région du client est obligatoire pour la mise à jour.");
        }
        localiteDao.update(id, regionClient);
    }

    @Override
    public void delete(int id) {
        localiteDao.delete(id);
    }
}
