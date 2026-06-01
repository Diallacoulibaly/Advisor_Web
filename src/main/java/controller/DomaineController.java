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
        String actions=req.getParameter("actions");
        if (actions==null) {
            List<Domaine> domaines = domaineService.afficher();
            req.setAttribute("domaines" ,domaines);
            req.getRequestDispatcher("/WEB-INF/view/pages/index_domaine.jsp").forward(req,resp);

        }
        else if (actions.equalsIgnoreCase("addDomaine")  ){
            req.getRequestDispatcher("/WEB-INF/view/pages/add_domaine.jsp").forward(req,resp);
        }
        else if (actions.equalsIgnoreCase("editDomaine")) {
            int id=Integer.parseInt(req.getParameter("id"));
            Domaine d=domaineService.getById(id);
            req.setAttribute("domaine",d);
            req.getRequestDispatcher("/WEB-INF/view/pages/update_domaine.jsp").forward(req,resp);
        }
        else if (actions.equalsIgnoreCase("deleteDomaine")) {
            int id=Integer.parseInt(req.getParameter("id")) ;
            domaineService.supprimer(id);
            resp.sendRedirect("domaines");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actions=req.getParameter("actions");
        if (!actions.isEmpty()){
            if (actions.equalsIgnoreCase("addDomaine")){
       String domaine=req.getParameter("domaine");
       domaineService.ajouter(domaine);
       resp.sendRedirect("domaines");
    }else if (actions.equalsIgnoreCase("updateDomaine")){
                int id=Integer.parseInt(req.getParameter("id"));
                String nom=req.getParameter("domaine");
                domaineService.modifier(id,nom);
                resp.sendRedirect("domaines");
            }
        }}


}
