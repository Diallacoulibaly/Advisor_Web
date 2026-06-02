package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.ActiviteDaoImplement;
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.ServiceImplemente.ActiviteServiceImplement;
import main.java.model.ServiceImplemente.EtapeServiceImplement;
import main.java.model.classes.Activite;
import main.java.model.classes.Domaine;
import main.java.model.classes.Etape;
import main.java.model.dao.ActiviteDao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/etps")
public class lstEtape extends HttpServlet {
    private EtapeDaoImplement etapeDao;
    private EtapeServiceImplement service;
    private ActiviteServiceImplement activiteServiceImplement;
    @Override
    public void init() {
        etapeDao = new EtapeDaoImplement();
        service = new EtapeServiceImplement(etapeDao);
        ActiviteDao activiteDao = new ActiviteDaoImplement();
//        activiteServiceImplement = new ActiviteServiceImplement(activiteDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if (action==null) action = "liste";

        switch (action) {
            case "addEt":

                int idEtape=Integer.parseInt(req.getParameter("idEtape"));
                Optional<Etape> etape=service.etape(idEtape);
                List<Activite> activites=activiteServiceImplement.
                req.setAttribute("etape",etape);
                req.setAttribute("activites",activites);
                req.getRequestDispatcher("/WEB-INF/view/pages/activites.jsp").forward(req, resp);
                break;
            default:
        List<Etape> et =service.Les_etapes();
                req.setAttribute("et" ,et);
        req.getRequestDispatcher("/WEB-INF/view/pages/EtapeInterface.jsp").forward(req,resp);
        break;
    }
}}
