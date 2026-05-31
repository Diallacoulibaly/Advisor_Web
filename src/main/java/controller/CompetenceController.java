package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.CompetenceDaoImplement;
import main.java.model.ServiceImplemente.CompetenceServiceImplement;
import main.java.model.classes.Competence;
import main.java.model.dao.CompetenceDao;
import main.java.model.service.CompetenceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/competences")
public class CompetenceController extends HttpServlet {

    private CompetenceService competenceService;

    @Override
    public void init() {
        CompetenceDao dao = new CompetenceDaoImplement();
        competenceService = new CompetenceServiceImplement(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) action = "liste";

        switch (action) {

            case "ajouter":
                // Affiche le formulaire d'ajout
                req.getRequestDispatcher("/WEB-INF/view/pages/add_competence.jsp")
                        .forward(req, resp);
                break;

            case "editer":
                // Récupère la compétence et affiche le formulaire de modification
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Competence c = competenceService.getById(idEdit);
                req.setAttribute("competence", c);
                req.getRequestDispatcher("/WEB-INF/view/pages/update_competence.jsp")
                        .forward(req, resp);
                break;

            case "supprimer":
                // Supprime et redirige vers la liste
                int idSuppr = Integer.parseInt(req.getParameter("id"));
                competenceService.supprimer(idSuppr);
                resp.sendRedirect("competences");
                break;

            default:
                // Affiche la liste de toutes les compétences
                List<Competence> competences = competenceService.afficher();
                req.setAttribute("competences", competences);
                req.getRequestDispatcher("/WEB-INF/view/pages/index_competence.jsp")
                        .forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            resp.sendRedirect("competences");
            return;
        }

        switch (action) {

            case "ajouter":
                String nom = req.getParameter("nom");
                competenceService.ajouter(nom);
                resp.sendRedirect("competences");
                break;

            case "modifier":
                int id = Integer.parseInt(req.getParameter("id"));
                String nomModifie = req.getParameter("nom");
                competenceService.modifier(id, nomModifie);
                resp.sendRedirect("competences");
                break;

            default:
                resp.sendRedirect("competences");
                break;
        }
    }
}
