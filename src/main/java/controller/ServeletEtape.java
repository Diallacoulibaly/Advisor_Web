package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.classes.Etape;
import main.java.model.classes.Projet;
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.ServiceImplemente.EtapeServiceImplement;
import main.java.model.enums.StatutEtape;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/etape")
public class ServeletEtape extends HttpServlet {

    private EtapeDaoImplement etapeDao;
    private EtapeServiceImplement service;

    @Override
    public void init() {
        etapeDao = new EtapeDaoImplement();
        service = new EtapeServiceImplement(etapeDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        //Redirection vers le formulaire d'ajout
        if ("ajouter".equals(action)) {
            request.getRequestDispatcher("/WEB-INF/view/ajout_etape.jsp").forward(request, response);
        }
        //Redirection vers le formulaire de modification
        else if ("modifier".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Optional<Etape> etapeOpt = service.etape(id);
                if (etapeOpt.isPresent()) {
                    request.setAttribute("etape", etapeOpt.get());
                    request.getRequestDispatcher("/WEB-INF/view/modif_etape.jsp").forward(request, response);
                } else {
                    response.sendRedirect("etape");
                }
            } catch (Exception e) {
                response.sendRedirect("etape");
            }
        }
        //Supprimer directement
        else if ("supprimer".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                service.suppression(id);
                response.sendRedirect("etape");
            } catch (Exception e) {
                response.sendRedirect("etape");
            }
        }
        //Affichage de la liste par defaut
        else {
            List<Etape> etapes = service.Les_etapes();
            request.setAttribute("etapes", etapes);
            request.getRequestDispatcher("/WEB-INF/view/liste_etapes.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("Ajout".equals(action)) {
            try {
                String titre = request.getParameter("titre");
                String description = request.getParameter("description");
                int ordre = Integer.parseInt(request.getParameter("ordre"));
                StatutEtape statut = StatutEtape.valueOf(request.getParameter("statut"));
                int projetId = Integer.parseInt(request.getParameter("projetId"));

                Projet projet = new Projet();
                projet.setId(projetId);

                //Instanciation sans ID (géré par auto-incrément de la BDD)
                Etape nouvelleEtape = new Etape(0, titre, description, ordre, statut, projet);

                boolean success = service.ajoutt(nouvelleEtape);

                if (success) {
                    response.sendRedirect("etape");
                } else {
                    request.setAttribute("error", "Erreur lors de la création de l'étape");
                    request.getRequestDispatcher("/WEB-INF/view/ajout_etape.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Données invalides : " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/ajout_etape.jsp").forward(request, response);
            }
        }
        //Traitement de la modification
        else if ("Modif".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("idEtape"));
                String titre = request.getParameter("titre");
                String description = request.getParameter("description");
                int ordre = Integer.parseInt(request.getParameter("ordre"));
                StatutEtape statut = StatutEtape.valueOf(request.getParameter("statut"));
                int projetId = Integer.parseInt(request.getParameter("projetId"));

                Projet projet = new Projet();
                projet.setId(projetId);

                Etape etape = new Etape(id, titre, description, ordre, statut, projet);

                boolean success = service.miseAjour(etape);

                if (success) {
                    response.sendRedirect("etape");
                } else {
                    request.setAttribute("error", "Erreur lors de la mise à jour");
                    request.getRequestDispatcher("/WEB-INF/view/modif_etape.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("error", "Données invalides : " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/view/modif_etape.jsp").forward(request, response);
            }
        }
    }
}
