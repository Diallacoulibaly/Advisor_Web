package main.java.controller;

import main.java.model.DaoImplement.DepenseDaoImplement;
import main.java.model.ServiceImplemente.DepenseServiceImplement;
import main.java.model.classes.Depense;
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
public class DepenseServlet extends HttpServlet {

    private DepenseService depenseService;

    @Override
    public void init() {
        depenseService = new DepenseServiceImplement(new DepenseDaoImplement());
    }

    // ─── GET ──────────────────────────────────────────────────────────────────
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "liste";

        switch (action) {

            // ── Afficher toutes les dépenses ──────────────────────────────────
            case "liste" -> {
                List<Depense> depenses = depenseService.getAllDepenses();
                request.setAttribute("depenses", depenses);
                request.getRequestDispatcher("/depenses.jsp").forward(request, response);
            }

            // ── Supprimer une dépense ─────────────────────────────────────────
            case "supprimer" -> {
                String idParam = request.getParameter("id");
                if (idParam != null) {
                    depenseService.deleteDepense(Integer.parseInt(idParam));
                }
                response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
            }

            // ── Formulaire modifier (pré-remplissage) ─────────────────────────
            case "formulaire" -> {
                String idParam = request.getParameter("id");
                if (idParam != null) {
                    Optional<Depense> depense = depenseService.getDepenseById(Integer.parseInt(idParam));
                    depense.ifPresent(d -> request.setAttribute("depense", d));
                }
                request.getRequestDispatcher("/depenses.jsp").forward(request, response);
            }

            default -> response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
        }
    }

    // ─── POST ─────────────────────────────────────────────────────────────────
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

            // ── Ajouter une nouvelle dépense ──────────────────────────────────
            case "ajouter" -> {
                try {
                    double montant       = Double.parseDouble(request.getParameter("montant"));
                    String description   = request.getParameter("description");
                    Date   date          = Date.valueOf(request.getParameter("date")); // format attendu : YYYY-MM-DD
                    String idActiviteStr = request.getParameter("idActivite");
                    Integer idActivite   = (idActiviteStr != null && !idActiviteStr.isEmpty())
                            ? Integer.parseInt(idActiviteStr) : null;

                    Depense depense = new Depense(null, montant, description, date, idActivite);
                    depenseService.addDepense(depense);

                } catch (Exception e) {
                    request.setAttribute("erreur", "Données invalides : " + e.getMessage());
                    request.getRequestDispatcher("/depenses.jsp").forward(request, response);
                    return;
                }
            }

            // ── Modifier une dépense existante ────────────────────────────────
            case "modifier" -> {
                try {
                    int    id            = Integer.parseInt(request.getParameter("id"));
                    double montant       = Double.parseDouble(request.getParameter("montant"));
                    String description   = request.getParameter("description");
                    Date   date          = Date.valueOf(request.getParameter("date"));
                    String idActiviteStr = request.getParameter("idActivite");
                    Integer idActivite   = (idActiviteStr != null && !idActiviteStr.isEmpty())
                            ? Integer.parseInt(idActiviteStr) : null;

                    depenseService.updateDepense(id, montant, description, date, idActivite);

                } catch (Exception e) {
                    request.setAttribute("erreur", "Données invalides : " + e.getMessage());
                    request.getRequestDispatcher("/depenses.jsp").forward(request, response);
                    return;
                }
            }
        }

        // Pattern PRG : toujours rediriger après un POST
        response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
    }
}