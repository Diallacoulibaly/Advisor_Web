package main.java.model.ServiceImplemente;

import main.java.model.classes.Commentaire;
import main.java.model.classes.Domaine;
import main.java.model.classes.Etape;
import main.java.model.dao.CommentaireDao;
import main.java.model.dao.DomaineDao;
import main.java.model.service.CommentaireService;

import java.util.List;

public class CommentaireServiceImplement implements CommentaireService {
    public final CommentaireDao commentaireDao;
    public CommentaireServiceImplement(CommentaireDao commentaireDao){
        this.commentaireDao=commentaireDao;
    }
    @Override
    public void ajouter(String commentaire, Etape etape){
        if(commentaire==null||commentaire.trim().isEmpty()){
            System.out.println("message obligatoire");
            return;
        }
        Commentaire c=new Commentaire();
        c.setMessage(commentaire);
        c.setEtape(etape);
        commentaireDao.ajoutCmt(c);




    }
    @Override
    public void modifier(int id,String message){
        if(id<0){
            System.out.println("id obligatoire");
            return;
        }
        Commentaire c=new Commentaire(id,message);
        commentaireDao.modifierCmt(c);
    }
    @Override
    public void supprimer(int id) {
        commentaireDao.supprimerCmt(id);
    }

    @Override
    public List<Commentaire> afficher() {
        return commentaireDao.afficherCmt();
    }
    @Override
    public Commentaire getById(int id){
        return  commentaireDao.getById(id);
    }
}
