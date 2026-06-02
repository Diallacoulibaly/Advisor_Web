package main.java.controller;

import main.java.model.classes.Activite;
import main.java.model.classes.Depense;
import main.java.model.ServiceImplemente.DepenseImplement;
import main.java.model.dao.DepenseDao;
import main.java.model.DaoImplement.DepenseDaoImplement;
import main.java.model.service.DepenseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet("/depenses")
public class DepenseController extends HttpServlet {

    private DepenseService depenseService;

    @Override
    public void init() {
        // CORRECTION: instanciation correcte du DAO et du service
        DepenseDao depenseDao = new DepenseDaoImplement();
        depenseService = new DepenseImplement(depenseDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "liste";

        switch (action) {
            case "liste" -> {
                List<Depense> depenses = depenseService.getAll();
                request.setAttribute("depenses", depenses);
                request.getRequestDispatcher("/WEB-INF/views/depenses/liste.jsp").forward(request, response);
            }
            case "supprimer" -> {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    depenseService.delete(Integer.parseInt(idParam));
                }
                response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
            }
            case "formulaire" -> {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    Optional<Depense> depense = depenseService.getById(Integer.parseInt(idParam));
                    depense.ifPresent(d -> request.setAttribute("depense", d));
                }
                request.getRequestDispatcher("/WEB-INF/views/depenses/form.jsp").forward(request, response);
            }
            default -> response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
            return;
        }

        switch (action) {
            case "ajouter" -> {
                try {
                    double montant = Double.parseDouble(request.getParameter("montant"));
                    String description = request.getParameter("description");
                    Date date = Date.valueOf(request.getParameter("date"));

                    // CORRECTION: récupération de l'activité si nécessaire
                    String activiteIdParam = request.getParameter("idActivite");
                    Depense depense;
                    if (activiteIdParam != null && !activiteIdParam.isEmpty()) {
                        Activite activite = new Activite();
                        activite.setId(Integer.parseInt(activiteIdParam));
                        depense = new Depense(null, montant, description, date, activite);
                    } else {
                        depense = new Depense(null, montant, description, date);
                    }

                    depenseService.add(depense);
                } catch (Exception e) {
                    request.setAttribute("erreur", "Données invalides : " + e.getMessage());
                    request.getRequestDispatcher("/WEB-INF/view/pages/activite.jsp").forward(request, response);
                    return;
                }
            }
            case "modifier" -> {
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    double montant = Double.parseDouble(request.getParameter("montant"));
                    String description = request.getParameter("description");
                    Date date = Date.valueOf(request.getParameter("date"));

                    // CORRECTION: créer un objet Depense complet pour la modification
                    Depense depense = new Depense(id, montant, description, date);
                    depenseService.update(depense);
                } catch (Exception e) {
                    request.setAttribute("erreur", "Données invalides : " + e.getMessage());
                    request.getRequestDispatcher("/WEB-INF/view/pages/activite.jsp").forward(request, response);
                    return;
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
    }
}