package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.dao.ProjetDao;
import model.enums.Niveau;
import model.DaoImplement.ProjetDaoImpl;
import model.classes.Projet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/projets")
public class ProjetController extends HttpServlet {

    // private ProjetDao projetDAO;

    // @Override
    // public void init() throws ServletException {
    //     this.projetDAO = new ProjetDaoImpl();
    // }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // try {
        //     List<Projet> listeProjets = projetDAO.findAll();
        //     request.setAttribute("projets", listeProjets);
        //     request.getRequestDispatcher("/WEB-INF/view/projets.jsp").forward(request, response);
            
        // } catch (SQLException e) {
        //     throw new ServletException("Erreur lors de la récupération des projets", e);
        // }

        List<Projet> listeProjets = new ArrayList<>();

        Projet p1 = new Projet();
        p1.setTitre("Agriculture Urbaine");
        p1.setNiveau(Niveau.DEBUTANT);
        p1.setBudgetMax(50000.0);
        
        Projet p2 = new Projet();
        p2.setTitre("Digitalisation PME");
        p2.setNiveau(Niveau.INTERMEDIAIRE);
        p2.setBudgetMax(250000.0);
        
        listeProjets.add(p1);
        listeProjets.add(p2);

        request.setAttribute("projets", listeProjets);
    
        request.getRequestDispatcher("/WEB-INF/view/projets.jsp").forward(request, response);

    }
}