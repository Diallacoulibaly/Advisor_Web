package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.classes.Etape;
import main.java.model.dao.EtapeDao;
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.enums.StatutEtape;
import main.java.model.service.EtapeService;
import main.java.model.ServiceImplemente.EtapeServiceImplement;

import java.io.IOException;
import java.util.List;

@WebServlet("/etape")
public class EtapeController extends HttpServlet{
    private Etape etape;
    private EtapeService etapeService;
    private EtapeServiceImplement etapeServiceImplementt;
    private EtapeDaoImplement etapeDaoImplement;


    public void init(){
        EtapeDao etape = new EtapeDaoImplement();
         etapeService = new EtapeServiceImplement(etape);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Etape> etapes = etapeService.Les_etapes();
        req.setAttribute("etape" ,etapes);
        req.getRequestDispatcher("/WEB-INF/view/liste_etapes.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String etape=req.getParameter("etape");
        Etape etape1=new Etape();

        etape1.setTitre("titre");
        etape1.setDescription("description");
        etape1.setOrdre(1);
        etape1.setStatutEtape(StatutEtape.ENCOURS);
        etapeService.ajoutt(etape1);
        resp.sendRedirect("etape");

    }
}