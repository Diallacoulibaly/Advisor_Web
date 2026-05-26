package main.java.model.ServiceImplemente;

import main.java.model.classes.Domaine;
import main.java.model.dao.DomaineDao;
import main.java.model.service.DomaineService;

import java.util.List;

public class DomaineImplement implements DomaineService {
    public final DomaineDao domaineDao;
    public DomaineImplement(DomaineDao domaineDao){
        this.domaineDao=domaineDao;
    }
    @Override
    public void ajouter(String domaine){
        if(domaine==null||domaine.trim().isEmpty()){
            System.out.println("nom du domaine obligatoire");
            return;
        }
        Domaine d=new Domaine(domaine);
        domaineDao.ajoutDomaine(d);




    }
    @Override
    public void modifier(int id,String domaine){
        if(id<0){
            System.out.println("id obligatoire");
            return;
        }
        Domaine d=new Domaine(id,domaine);
        domaineDao.modifierDomaine(d);
    }
    @Override
    public void supprimer(int id) {
        domaineDao.supprimerDomaine(id);
    }

    @Override
    public List<Domaine> afficher() {
        return domaineDao.afficherDomaine();
    }
    @Override
    public Domaine getById(int id){
        return  domaineDao.getById(id);
    }


}
