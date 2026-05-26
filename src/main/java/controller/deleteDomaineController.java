package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.DomaineDaoImplement;
import main.java.model.ServiceImplemente.DomaineImplement;
import main.java.model.dao.DomaineDao;
import main.java.model.service.DomaineService;

import java.io.IOException;

@WebServlet("/delete-domaine")
public class deleteDomaineController extends HttpServlet {
    private DomaineService service;

    public void init() {
        DomaineDao dom = new DomaineDaoImplement();
        service = new DomaineImplement(dom);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=Integer.parseInt(req.getParameter("id")) ;
        service.supprimer(id);
        resp.sendRedirect("domaines");


    }
}
