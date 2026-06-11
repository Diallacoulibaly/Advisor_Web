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

        req.setAttribute("menuActif", "competences");

        switch (action) {

            case "ajouter":
                req.setAttribute("pageContent", "add_competence.jsp");
                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp")
                        .forward(req, resp);
                break;

            case "editer":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Competence c = competenceService.getById(idEdit);
                req.setAttribute("competence", c);
                req.setAttribute("pageContent", "update_competence.jsp");
                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp")
                        .forward(req, resp);
                break;

            case "supprimer":
                int idSuppr = Integer.parseInt(req.getParameter("id"));
                competenceService.supprimer(idSuppr);
                resp.sendRedirect("competences");
                break;

            default:
                List<Competence> competences = competenceService.afficher();
                req.setAttribute("competences", competences);
                req.setAttribute("pageContent", "index_competence.jsp");
                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp")
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
                competenceService.ajouter(req.getParameter("nom"));
                resp.sendRedirect("competences");
                break;

            case "modifier":
                int id = Integer.parseInt(req.getParameter("id"));
                competenceService.modifier(id, req.getParameter("nom"));
                resp.sendRedirect("competences");
                break;

            default:
                resp.sendRedirect("competences");
                break;
        }
    }
}
