package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.ProjetClientDAOImplement;
import main.java.model.DaoImplement.UtilisateurDaoImplement;
import main.java.model.ServiceImplemente.ProjetClientServiceImplement;
import main.java.model.ServiceImplemente.UtilisateurServiceImplement;
import main.java.model.classes.ProjetClient;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.ProjetClientDAO;
import main.java.model.dao.UtilisateurDao;
import main.java.model.service.ProjetClientService;

import java.io.IOException;
import java.util.List;

@WebServlet("/mes_projets")
public class ProjetClientController extends HttpServlet {

    private ProjetClientServiceImplement pcServiceImplement;
    public void init(){
        ProjetClientDAO pcDao= new ProjetClientDAOImplement();
        pcServiceImplement= new ProjetClientServiceImplement(pcDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur utilisateur =
                (Utilisateur) req.getSession()
                        .getAttribute("user");

        if(utilisateur == null){

            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req,resp);
            return;
        }

        List<ProjetClient> projets =
                pcServiceImplement.getByClient(utilisateur.getIdUtilisateur());

        req.setAttribute("projets", projets);


        req.setAttribute("pageContent", "mes_projets.jsp");
        req.setAttribute("menuActif", "mes_projets");

        req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
    }

}
