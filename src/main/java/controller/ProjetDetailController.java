package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.model.DaoImplement.*;
import main.java.model.ServiceImplemente.EtapeServiceImplement;
import main.java.model.ServiceImplemente.ProjetServiceImpl;
import main.java.model.ServiceImplemente.SuivieEtapeServiceImplement;
import main.java.model.classes.Etape;
import main.java.model.classes.Projet;
import main.java.model.classes.SuivieEtape;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.EtapeDao;
import main.java.model.dao.ProjetDao;
import main.java.model.dao.SuivieEtapeDao;
import main.java.model.service.SuivieEtapeService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/projetDetail")
public class ProjetDetailController extends HttpServlet {

    private ProjetServiceImpl projetServiceImpl;
    private EtapeServiceImplement etapeServiceImplement;
    private SuivieEtapeService suivieEtapeService;

    @Override
    public void init() {
        ProjetDao pDao = new ProjetDaoImpl();
        EtapeDao etapeDao = new EtapeDaoImplement();
        SuivieEtapeDao suivieEtapeDao = new SuivieEtapeDaoImplement();

        projetServiceImpl = new ProjetServiceImpl(pDao);
        etapeServiceImplement = new EtapeServiceImplement(etapeDao);
        suivieEtapeService = new SuivieEtapeServiceImplement(suivieEtapeDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProjet = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        Utilisateur clientConnecte = (Utilisateur) session.getAttribute("user");

        List<Etape> etapes = etapeServiceImplement.ListeEtapesByProjet(idProjet);
        Optional<Projet> projet = projetServiceImpl.getProjetById(idProjet);

        if (clientConnecte != null) {
            int idClient = clientConnecte.getIdUtilisateur();
            List<SuivieEtape> suivis = suivieEtapeService.obtenirSuivisParProjetEtClient(idProjet, idClient);
            request.setAttribute("suivis", suivis);
        }

        if (projet.isPresent()) {
            request.setAttribute("projet", projet.get());
        }

        request.setAttribute("etapes", etapes);
        request.setAttribute("pageContent", "Cartes_etape.jsp");
        request.setAttribute("menuActif", "mes_projets");

        request.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(request, response);
    }
}
