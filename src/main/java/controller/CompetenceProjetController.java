package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.CompetenceProjetDaoImplement;
import main.java.model.ServiceImplemente.CompetenceProjetImplement;
import main.java.model.classes.CompetenceProjet;
import main.java.model.dao.CompetenceProjetDao;
import main.java.model.service.CompetenceProjetService;

import java.io.IOException;
import java.util.List;

@WebServlet("/competenceprojet")
public class CompetenceProjetController extends HttpServlet {
    private CompetenceProjetDao cpDao;
    private CompetenceProjetService service;

    @Override
    public void init() {
        cpDao = new CompetenceProjetDaoImplement();
        service = new CompetenceProjetImplement(cpDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("ajouter".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/view/ajout_competence_projet.jsp").forward(request, response);
        }
        else if ("modifier".equals(action)) {
            try {
                int compId = Integer.parseInt(request.getParameter("competenceId"));
                int projId = Integer.parseInt(request.getParameter("projetId"));

                CompetenceProjet cpT = service.rechercher(projId, compId);

                if (cpT != null) {
                    request.setAttribute("competenceProjet", cpT);
                    request.setAttribute("ancienCompetenceId", compId);
                    request.setAttribute("ancienProjetId", projId);

                    request.getRequestDispatcher("/WEB-INF/view/modif_competence_projet.jsp").forward(request, response);
                } else {
                    response.sendRedirect("competenceprojet");
                }
            } catch (Exception e) {
                System.err.println("Erreur redirection modification : " + e.getMessage());
                response.sendRedirect("competenceprojet");
            }
        }
        else if ("supprimer".equals(action)) {
            try {
                int compId = Integer.parseInt(request.getParameter("competenceId"));
                int projId = Integer.parseInt(request.getParameter("projetId"));

                service.supprimer(projId, compId);
                response.sendRedirect("competenceprojet");
            } catch (Exception e) {
                response.sendRedirect("competenceprojet");
            }
        }
        // Affichage de la liste par défaut
        else {
            List<CompetenceProjet> liste = service.lister();
            request.setAttribute("competenceProjets", liste);
            request.getRequestDispatcher("/WEB-INF/view/liste_competence_projets.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // Traitement de l'ajout
        if ("AjoutCP".equals(action)) {
            try {
                int competenceId = Integer.parseInt(request.getParameter("competenceId"));
                int projetId = Integer.parseInt(request.getParameter("projetId"));

                CompetenceProjet cp = new CompetenceProjet(competenceId, projetId);
                boolean success = service.ajouter(cp);

                if (success) {
                    response.sendRedirect("competenceprojet");
                } else {
                    request.setAttribute("error", "Erreur lors de l'association de la compétence au projet.");
                    request.getRequestDispatcher("/WEB-INF/view/ajout_competence_projet.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Données invalides : " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/ajout_competence_projet.jsp").forward(request, response);
            }
        }
        // Traitement de la modification
        else if ("ModifCP".equals(action)) {
            try {
                int ancienCompetenceId = Integer.parseInt(request.getParameter("ancienCompetenceId"));
                int ancienProjetId = Integer.parseInt(request.getParameter("ancienProjetId"));

                int nouvelleCompetenceId = Integer.parseInt(request.getParameter("competenceId"));
                int nouveauProjetId = Integer.parseInt(request.getParameter("projetId"));

                CompetenceProjet cp = new CompetenceProjet(nouvelleCompetenceId, nouveauProjetId);

                boolean success = service.miseAjour(ancienProjetId, ancienCompetenceId, cp);

                if (success) {
                    response.sendRedirect("competenceprojet");
                } else {
                    request.setAttribute("error", "Erreur lors de la mise à jour");
                    request.getRequestDispatcher("/WEB-INF/view/modif_competence_projet.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Données invalides : " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/modif_competence_projet.jsp").forward(request, response);
            }
        }
    }
}
