package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.HistoriqueDaoImplement;
import main.java.model.ServiceImplemente.HistoriqueServiceImplement;
import main.java.model.classes.Historique;
import main.java.model.dao.HistoriqueDao;

import java.io.IOException;
import java.util.List;


@WebServlet("/historique")
public class HistoriqueController extends HttpServlet {
    private HistoriqueServiceImplement historiqueServiceImplement;

    @Override
    public void init() throws ServletException {
        HistoriqueDao historiqueDao = new HistoriqueDaoImplement();
        historiqueServiceImplement  = new HistoriqueServiceImplement(historiqueDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Historique> historiqueList = historiqueServiceImplement.afficherHistorique();

        req.setAttribute("historiqueList", historiqueList);
        req.setAttribute("pageContent", "historique.jsp");
        req.setAttribute("menuActif", "historique");
        req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}