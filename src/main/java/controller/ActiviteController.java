package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.ActiviteDaoImplement;
import main.java.model.ServiceImplemente.ActiviteServiceImplement;
import main.java.model.classes.Activite;
import main.java.model.dao.ActiviteDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/activite")
public class ActiviteController extends HttpServlet {
    private ActiviteServiceImplement activiteServiceImplement;

    @Override
    public void init() throws ServletException {
        ActiviteDao activiteDao = new ActiviteDaoImplement();
        activiteServiceImplement = new ActiviteServiceImplement(activiteDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Activite> activiteList = activiteServiceImplement.afficherActivite();
        req.setAttribute("activiteList", activiteList);
        req.getRequestDispatcher("/WEB-INF/view/pages/activite.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}