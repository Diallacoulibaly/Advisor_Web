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
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.enums.StatutEtape;
import main.java.model.service.EtapeService;

import java.io.IOException;

@WebServlet("/modif_etape")
public class Modif_etape extends HttpServlet {

    private EtapeService service;

    public void init() {
        EtapeDao dao = new EtapeDaoImplement();
        service = new EtapeServiceImplement(dao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        service.etape(id);

        req.setAttribute("etape", id);
        req.getRequestDispatcher("/WEB-INF/view/modif_etape.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("idEtape"));
        String titre = req.getParameter("titre");
        String description = req.getParameter("description");
        int ordre = Integer.parseInt(req.getParameter("ordre"));
        String statutStr = req.getParameter("statut");
        int projetId = Integer.parseInt(req.getParameter("projetId"));

        StatutEtape statut = StatutEtape.valueOf(statutStr);
        Projet projet = new Projet();
        projet.setId(projetId);

        Etape etape = new Etape(id, titre, description, ordre, statut, projet);

        boolean success = service.miseAjour(etape);

        if (success) {
            resp.sendRedirect("etape");
        } else {
            req.setAttribute("error", "Erreur lors de la modification");
            req.getRequestDispatcher("/WEB-INF/view/modif_etape.jsp").forward(req, resp);
        }
    }
}