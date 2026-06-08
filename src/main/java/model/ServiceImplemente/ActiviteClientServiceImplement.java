package main.java.model.ServiceImplemente;

import main.java.model.dao.ActiviteCloentDao;
import main.java.model.service.ActiviteClientService;

public class ActiviteClientServiceImplement implements ActiviteClientService {
    private ActiviteCloentDao activiteClientDao;
    public ActiviteClientServiceImplement (ActiviteCloentDao activiteClientDao){
        this.activiteClientDao = activiteClientDao;
    }

    @Override
    public void marquerTerminer(int id) {
        activiteClientDao.marquerTerminer(id);
    }
}
