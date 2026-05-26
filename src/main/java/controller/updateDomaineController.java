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

@WebServlet("/update-domaine")
public class updateDomaineController extends HttpServlet {
    private DomaineService service;

    public void init() {
        DomaineDao dao = new DomaineDaoImplement();
        service = new DomaineImplement(dao);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   int id=Integer.parseInt(req.getParameter("id"));
   Domaine d=service.getById(id);
   req.setAttribute("domaine",d);
   req.getRequestDispatcher("/WEB-INF/view/update_domaine.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id"));
        String nom=req.getParameter("domaine");
        System.out.println("ID = " + req.getParameter("id"));
        System.out.println("DOMAINE = " + req.getParameter("domaine"));
        service.modifier(id,nom);
        resp.sendRedirect("domaines");
    }


}
