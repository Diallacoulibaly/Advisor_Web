package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.model.DaoImplement.UtilisateurDaoImplement;
import main.java.model.ServiceImplemente.UtilisateurServiceImplement;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.UtilisateurDao;
import main.java.model.enums.Role;

import java.io.IOException;
@WebServlet("/connexion")
public class ConnexionController extends HttpServlet {
    private UtilisateurServiceImplement utilisateurServiceImplement;
    public void init(){
        UtilisateurDao utilisateurDao= new UtilisateurDaoImplement();
        utilisateurServiceImplement= new UtilisateurServiceImplement(utilisateurDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


                req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req, resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        Object response= utilisateurServiceImplement.authentifierUtilisateur(email, password);
        if(!(response instanceof Utilisateur)){
            req.setAttribute("erreur", "Identifiant incorrect!!!!");
            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp");
//            resp.getWriter().print("Identifiant incorrect!!!!");
        }

        Utilisateur utilisateur= (Utilisateur) response;
        HttpSession session=req.getSession();
        session.setAttribute("user", utilisateur);
        if(utilisateur.getRole()== Role.CLIENT){
            req.setAttribute("pageContent", "accueilClient.jsp");
            req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("pageContent", "dashboard.jsp");
            req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
        }


    }
}
