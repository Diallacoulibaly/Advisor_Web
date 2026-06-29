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
import main.java.model.classes.Etape;
import main.java.model.dao.ActiviteDao;
import main.java.model.dao.EtapeDao;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/etape_activite")
public class ActiviteEtapeController extends HttpServlet {
        private ActiviteServiceImplement activiteServiceImplement;
        private EtapeServiceImplement etapeServiceImplement;
        @Override
        public void init() throws ServletException {
            ActiviteDao activiteDao = new ActiviteDaoImplement();
            EtapeDao etapeDao = new EtapeDaoImplement();
            activiteServiceImplement = new ActiviteServiceImplement(activiteDao);
            etapeServiceImplement = new EtapeServiceImplement(etapeDao);
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int idEtape = Integer.parseInt(req.getParameter("idEtape"));
            Optional<Etape> etapeOpt = etapeServiceImplement.etape(idEtape);
            etapeOpt.ifPresent(etape -> req.setAttribute("etapeObject", etape));
            System.out.println("etape: "+ etapeOpt.get().getIdEtape() + " titre: " +etapeOpt.get().getTitre());
            String etapeTitre = req.getParameter("titreEtape");
            String descEtape=req.getParameter("descEtape");
            int idProjet = Integer.parseInt(req.getParameter("idProjet"));
            List<Activite> activiteList = activiteServiceImplement.getActiviteByEtape(idEtape);
            req.setAttribute("activiteList", activiteList);
            req.setAttribute("idEtape",idEtape);
            req.setAttribute("titreEtape", etapeTitre);
            req.setAttribute("descEtape", descEtape);
            req.setAttribute("idProjet", idProjet);

            req.setAttribute("pageContent","activite.jsp");
            req.setAttribute("menuActif", "mes_projets");

            req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req,resp);

        }

//        @Override
//        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//            String action = req.getParameter("action");
//
//            if (action.equals("terminer")){
//                int id = Integer.parseInt(req.getParameter("id"));
//                activiteServiceImplement.marquerTerminer(id);
//                resp.sendRedirect("activite");
//
//            } else if (action.equals("supprimer")) {
//                int id = Integer.parseInt(req.getParameter("id"));
//                activiteServiceImplement.supprimerActivite(id);
//                resp.sendRedirect("activite");
//
//            }
//        }
    }


