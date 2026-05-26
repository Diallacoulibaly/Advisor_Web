package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.DomaineDaoImplement;
import main.java.model.ServiceImplemente.DomaineImplement;
import main.java.model.classes.Domaine;
import main.java.model.dao.DomaineDao;
import main.java.model.service.DomaineService;

import java.io.IOException;
import java.util.List;
@WebServlet("/domaines")
public class DomaineController extends HttpServlet {
    private DomaineService domaineService;
    private Domaine domaine;
    private DomaineDaoImplement domaineDaoImplement;
    public void init(){
        DomaineDao dom=new DomaineDaoImplement();
        domaineService =
                new DomaineImplement(dom);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Domaine> domaines = domaineService.afficher();
        req.setAttribute("domaines" ,domaines);
        req.getRequestDispatcher("/WEB-INF/view/index_domaine.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String domaine=req.getParameter("domaine");
       domaineService.ajouter(domaine);
       resp.sendRedirect("domaines");
    }


}
