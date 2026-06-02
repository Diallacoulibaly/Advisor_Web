package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.CommentaireDaoImplement;
import main.java.model.DaoImplement.DomaineDaoImplement;
import main.java.model.ServiceImplemente.CommentaireServiceImplement;
import main.java.model.ServiceImplemente.DomaineImplement;
import main.java.model.classes.Commentaire;
import main.java.model.classes.Domaine;
import main.java.model.dao.CommentaireDao;
import main.java.model.dao.DomaineDao;
import main.java.model.service.CommentaireService;
import main.java.model.service.DomaineService;

import java.io.IOException;
import java.util.List;

@WebServlet("/commentaires")
public class CommentaireController extends HttpServlet {
    private CommentaireService commentaireService;
    private Commentaire commentaire;
    private CommentaireDaoImplement commentaireDaoImplement;
    public void init(){
        CommentaireDao com=new CommentaireDaoImplement();
        commentaireService =new CommentaireServiceImplement(com);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actions=req.getParameter("actions");
        if (actions==null) actions = "listeCommentaire";

        switch (actions) {
            case "addCmt":
                req.getRequestDispatcher("/WEB-INF/view/pages/add_commentaire.jsp").forward(req, resp);
                break;
            case "editCmt":
                int id=Integer.parseInt(req.getParameter("id"));
                Commentaire c=commentaireService.getById(id);
                req.setAttribute("cmt",c);
                req.getRequestDispatcher("/WEB-INF/view/pages/update_commentaire.jsp").forward(req,resp);
                break;
            case "deleteCmt":
                int idD=Integer.parseInt(req.getParameter("id")) ;
                commentaireService.supprimer(idD);
                resp.sendRedirect("commentaires");
                break;
            default:
                List<Commentaire> cmt = commentaireService.afficher();
                req.setAttribute("cmt" ,cmt);
                req.getRequestDispatcher("/WEB-INF/view/pages/index_commentaire.jsp").forward(req,resp);
                break;


        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actions=req.getParameter("actions");
        if (!actions.isEmpty()){
            if (actions.equalsIgnoreCase("addCmt")){
                String cmt=req.getParameter("cmt");
                commentaireService.ajouter(cmt);
                resp.sendRedirect("commentaires");

            }
            else if (actions.equalsIgnoreCase("updateCmt")){
                int id=Integer.parseInt(req.getParameter("id"));
                String msg=req.getParameter("cmt");
                commentaireService.modifier(id,msg);
                resp.sendRedirect("commentaires");
            }
        }

    }

}
