package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.ActiviteClientDaoImplement;
import main.java.model.DaoImplement.ActiviteDaoImplement;
import main.java.model.ServiceImplemente.ActiviteClientServiceImplement;
import main.java.model.ServiceImplemente.ActiviteServiceImplement;
import main.java.model.dao.ActiviteCloentDao;
import main.java.model.dao.ActiviteDao;

import java.io.IOException;

@WebServlet("/activite_client")
public class ActiviteClientController extends HttpServlet {
    private ActiviteClientServiceImplement activiteClientServiceImplement;

    @Override
    public void init() throws ServletException {
        ActiviteCloentDao activiteClientDao = new ActiviteClientDaoImplement();
        activiteClientServiceImplement = new ActiviteClientServiceImplement(activiteClientDao);
    }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");

            if (action.equals("depenses")){
                int idActivite = Integer.parseInt(req.getParameter("idActivite"));
                activiteClientServiceImplement.marquerTerminer(idActivite);
                resp.sendRedirect("activite");

            }
        }

}
