package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.ServiceImplemente.EtapeServiceImplement;
import main.java.model.classes.Etape;
import main.java.model.classes.Projet;
import main.java.model.dao.EtapeDao;
import main.java.model.enums.StatutEtape;
import main.java.model.service.EtapeService;

import java.io.IOException;




@WebServlet("/ajout_etape")
public class AjoutEtape extends HttpServlet {

    private EtapeService service;
    public void init() {
        EtapeDaoImplement dao = new EtapeDaoImplement();
        service = new EtapeServiceImplement(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Redirecton
        req.getRequestDispatcher("/WEB-INF/view/ajout_etape.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        int ordre = Integer.parseInt(req.getParameter("ordre"));
        String statutStr = req.getParameter("statut");
        int projetId = Integer.parseInt(req.getParameter("projetId"));

        StatutEtape statut = StatutEtape.valueOf(statutStr);
        Projet projet = new Projet();
        projet.setId(projetId);

        Etape etape = new Etape(0, titre, description, ordre, statut, projet);

        boolean success = service.ajoutt(etape);

        if (success) {
            resp.sendRedirect("etape");
        } else {
            req.setAttribute("error", "Erreur lors de l'ajout de l'étape");
            req.getRequestDispatcher("/WEB-INF/view/ajout_etape.jsp").forward(req, resp);
        }
    }
}