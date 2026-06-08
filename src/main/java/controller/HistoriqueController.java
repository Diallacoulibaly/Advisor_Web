package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.DaoImplement.HistoriqueDaoImplement;
import main.java.model.ServiceImplemente.EtapeServiceImplement;
import main.java.model.ServiceImplemente.HistoriqueServiceImplement;
import main.java.model.classes.Historique;
import main.java.model.classes.HistoriqueProjet;
import main.java.model.classes.Projet;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.EtapeDao;
import main.java.model.dao.HistoriqueDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/historique")
public class HistoriqueController extends HttpServlet {
    private HistoriqueServiceImplement historiqueServiceImplement;
    private EtapeServiceImplement etapeServiceImplement;

    @Override
    public void init() throws ServletException {
        EtapeDao etapeDao= new EtapeDaoImplement();
        etapeServiceImplement= new EtapeServiceImplement(etapeDao);

        HistoriqueDao historiqueDao = new HistoriqueDaoImplement();
        historiqueServiceImplement  = new HistoriqueServiceImplement(historiqueDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur utilisateur =
                (Utilisateur) req.getSession()
                        .getAttribute("user");

        if(utilisateur == null){

            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req,resp);
            return;
        }
        List<HistoriqueProjet> historiqueList = historiqueServiceImplement.afficherHistoriqueClient(utilisateur.getIdUtilisateur());
        Map<Integer, Integer> nbEtapesMap = new HashMap<>();
        for (HistoriqueProjet p : historiqueList) {
            nbEtapesMap.put(p.getProjet().getId(), etapeServiceImplement.countEtapes(p.getProjet().getId()));
        }
        req.setAttribute("NbreEtapes", nbEtapesMap);

        req.setAttribute("historiqueList", historiqueList);
        req.setAttribute("pageContent", "historique.jsp");
        req.setAttribute("menuActif", "historique");
        req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}