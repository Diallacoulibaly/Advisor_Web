package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.LocaliteDaoImplement;
import main.java.model.ServiceImplemente.LocaliteServiceImplemente;
import main.java.model.classes.Localite;
import main.java.model.dao.LocaliteDao;

import java.io.IOException;
import java.util.List;

@WebServlet("/localites")
public class LocaliteController extends HttpServlet {
    private LocaliteServiceImplemente localiteServiceImplemente;
    public void init(){
        LocaliteDao l=new LocaliteDaoImplement();
         localiteServiceImplemente=new LocaliteServiceImplemente(l);


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String actions=req.getParameter("actions");
       if (actions==null) actions = "liste";

       switch (actions) {
           case "add":
               req.getRequestDispatcher("/WEB-INF/view/add_localite.jsp").forward(req, resp);
               break;
           case "edit":
               int id = Integer.parseInt(req.getParameter("id"));
               Localite l = localiteServiceImplemente.getById(id);
               req.setAttribute("localite", l);
               req.getRequestDispatcher("/WEB-INF/view/edit_localite.jsp").forward(req, resp);
               break;
           case "delete":
               int idl = Integer.parseInt(req.getParameter("id"));
               localiteServiceImplemente.delete(idl);
               resp.sendRedirect("localites");
               break;
           default:
               List<Localite> lstLocalite = localiteServiceImplemente.getAll();
               req.setAttribute("lstLocalite", lstLocalite);
               req.getRequestDispatcher("/WEB-INF/view/index_localite.jsp").forward(req, resp);
               break;
       }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actions = req.getParameter("actions");
        if (actions != null && !actions.isEmpty()) {
            if (actions.equalsIgnoreCase("add")) {
               String regionClient=req.getParameter("regionClient");
               Localite localite=new Localite();
               localite.setRegionClient(regionClient);
               localiteServiceImplemente.add(localite);
               resp.sendRedirect("localites");
           }
           else if (actions.equalsIgnoreCase("update")){
               int id=Integer.parseInt(req.getParameter("id"));
               String regionClient=req.getParameter("regionClient");
               localiteServiceImplemente.update(id,regionClient);
               resp.sendRedirect("localites");


           }
       }
    }


}
