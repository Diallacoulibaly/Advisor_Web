package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import main.java.model.ServiceImplemente.ProjetServiceImpl;
import main.java.model.dao.ProjetDao;
import main.java.model.enums.Niveau;
import main.java.model.DaoImplement.ProjetDaoImpl;
import main.java.model.classes.Projet;
import main.java.model.service.ProjetService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/projets")
public class ProjetController extends HttpServlet {
    private ProjetService service;

    public void init() {
        ProjetDao p = new ProjetDaoImpl();
        service = new ProjetServiceImpl(p);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id;
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                request.getRequestDispatcher("/WEB-INF/view/formProjet.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                Optional<Projet> projet = service.getProjetById(id);
                request.setAttribute("projet", projet);
                request.getRequestDispatcher("/WEB-INF/view/formProjet.jsp").forward(request, response);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                service.supprimerProjet(id);
                response.sendRedirect("projets");
                break;
            default:
                List<Projet> liste = service.getAllProjets();
                request.setAttribute("projets", liste);
                request.getRequestDispatcher("/WEB-INF/view/projets.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("save".equals(action)) {
            enregistrerProjet(request, response);
        }
    }

    private void enregistrerProjet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            String titre = request.getParameter("titre");
            String description = request.getParameter("description");
            float duree = Float.parseFloat(request.getParameter("duree"));
            String niveau = request.getParameter("niveau");
            double budgetMin = Double.parseDouble(request.getParameter("budgetMin"));
            double budgetMax = Double.parseDouble(request.getParameter("budgetMax"));

            Projet p = new Projet();

            p.setTitre(titre);
            p.setDescription(description);
            p.setDuree(duree);
            p.setNiveau(Niveau.valueOf(niveau));
            p.setBudgetMin(budgetMin);
            p.setBudgetMax(budgetMax);

            service.enregistrerProjet(p);
            response.sendRedirect("projets");

        } catch (IllegalArgumentException e) {
            // plus tard : utiliser requestDispatcher pour afficher e
            response.sendRedirect("projets?action=create&erreur=donnees_invalides");
        }
    }

}








