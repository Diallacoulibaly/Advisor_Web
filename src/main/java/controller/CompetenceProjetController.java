package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.classes.CompetenceProjet;
import main.java.model.DaoImplement.CompetenceProjetDaoImplement;
import main.java.model.ServiceImplemente.CompetenceProjetImplement;
import main.java.model.dao.CompetenceProjetDao;
import main.java.model.service.CompetenceProjetService;

import java.io.IOException;
import java.util.List;

@WebServlet("/competenceProjet")
public class CompetenceProjetController extends HttpServlet {

    private CompetenceProjetDao competenceProjetDao;
    private CompetenceProjetService service;

    @Override
    public void init() {
        competenceProjetDao = new CompetenceProjetDaoImplement();
        service = new CompetenceProjetService(competenceProjetDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        //Redirection vers le formulaire d'ajout
        if ("ajouter".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/view/ajout_competence_projet.jsp").forward(request, response);
        }
        //Redirction vers le formulaire de modification
        else if ("modifier".equals(action)) {
            try {
                //Récupération des deux identifiants de la clé composite
                int compId = Integer.parseInt(request.getParameter("competenceId"));
                int projId = Integer.parseInt(request.getParameter("projetId"));

                //Recherche de l'objet correspondant dans la liste complète
                CompetenceProjet cpT = service.lister().stream()
                        .filter(cp -> cp.getCompetenceId() == compId && cp.getIdProjet() == projId)
                        .findFirst()
                        .orElse(null);

                if (cpT != null) {
                    request.setAttribute("competenceProjet", cpT);
                    request.getRequestDispatcher("/WEB-INF/view/modif_competence_projet.jsp").forward(request, response);
                } else {
                    response.sendRedirect("competenceProjet");
                }
            } catch (Exception e) {
                System.err.println("Erreur redirection modification : " + e.getMessage());
                response.sendRedirect("competenceProjet");
            }
        }
        //Suppression direct
        else if ("supprimer".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                service.supprimer(id);
                response.sendRedirect("competenceProjet");
            } catch (Exception e) {
                response.sendRedirect("competenceProjet");
            }
        }

        //Affichage de la liste par defaut
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

        //Traitement de l'ajout
        if ("AjoutCP".equals(action)) {
            try {
                int competenceId = Integer.parseInt(request.getParameter("competenceId"));
                int projetId = Integer.parseInt(request.getParameter("projetId"));

                CompetenceProjet cp = new CompetenceProjet(competenceId, projetId);

                boolean success = service.ajouter(cp);

                if (success) {
                    response.sendRedirect("competenceProjet");
                } else {
                    request.setAttribute("error", "Erreur lors de l'association de la compétence au projet.");
                    request.getRequestDispatcher("/WEB-INF/view/ajout_competence_projet.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Données invalides : " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/ajout_competence_projet.jsp").forward(request, response);
            }
        }

        //Traitement de la modification
        else if ("ModifCP".equals(action)) {
            try {
                //Nouvelles valeurs saisies dans le formulaire
                int nouvelleCompetenceId = Integer.parseInt(request.getParameter("competenceId"));
                int nouveauProjetId = Integer.parseInt(request.getParameter("projetId"));

                CompetenceProjet cp = new CompetenceProjet(nouvelleCompetenceId, nouveauProjetId);

                boolean success = service.mettre_a_jour(cp);

                if (success) {
                    response.sendRedirect("competenceProjet");
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

