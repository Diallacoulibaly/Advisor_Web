package main.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.model.classes.Etape;

@WebServlet("/etape")
public class ServletEtape_test extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EtapeDao etapeDao = new EtapeDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("miseAjour".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));

// Récupérer l'étape existante depuis la BDD
                Etape etape = EtapeDao.rech_etape(idEtape);
                Etape etape = new Etape();
                if (etape != null) {
// Transmettre l'objet à la JSP de modification
                    request.setAttribute("etape", etape);
                    request.getRequestDispatcher("/modif_etape.jsp").forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/etape");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/etape");
            }
        } else if ("suppression".equals(action)) {
// Logique de suppression
            int idEtape = Integer.parseInt(request.getParameter("idEtape"));
            EtapeDao.suppr_etape(idEtape);
            response.sendRedirect(request.getContextPath() + "/etape");
        } else {
// Affichage par défaut de la liste si aucune action spécifiée
            List<Etape> etapes = EtapeDao.Liste_etape();
            request.setAttribute("etape", etapes);
            request.getRequestDispatcher("/liste_etapes.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("enregistrerModif".equals(action)) {
            String idStr = request.getParameter("idEtape");
            String titre = request.getParameter("titre");
            String description = request.getParameter("description");
            String ordreStr = request.getParameter("ordre");
            String statut = request.getParameter("statut");
            String projetIdStr = request.getParameter("projetId");

            try {
                int id = Integer.parseInt(idStr);
                int ordre = Integer.parseInt(ordreStr);
                int projetId = Integer.parseInt(projetIdStr);

// Mettre à jour l'objet
                Etape etapeModifiee = new Etape(id, titre, description, ordre, statut, projetId);

// Enregistrer en base de données
                EtapeDao.mise_a_jour_etape(etapeModifiee);

// Redirection vers la liste
                response.sendRedirect(request.getContextPath() + "/etape");

            } catch (NumberFormatException e) {
                request.setAttribute("error", "Veuillez saisir des valeurs numériques valides.");
// Recharger l'objet initial pour éviter un formulaire vide en cas d'échec
                request.getRequestDispatcher("/modif_etape.jsp").forward(request, response);
            }
        }
    }
}

