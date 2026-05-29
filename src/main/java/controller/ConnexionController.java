package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.model.DaoImplement.UtilisateurDaoImplement;
import main.java.model.ServiceImplemente.UtilisateurServiceImplement;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.UtilisateurDao;
import main.java.model.enums.Role;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/connexion")
public class ConnexionController extends HttpServlet {
    private UtilisateurServiceImplement utilisateurServiceImplement;
    public void init(){
        UtilisateurDao utilisateurDao= new UtilisateurDaoImplement();
        utilisateurServiceImplement= new UtilisateurServiceImplement(utilisateurDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


                req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req, resp);



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        Optional<Utilisateur> utilisateur = Optional.of(new Utilisateur());

        int response= utilisateurServiceImplement.authentifierUtilisateur(email, password);
        if(response==0){
            req.setAttribute("erreur", "Identifiant incorrect !");
            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").include(req, resp);
//            resp.getWriter().print("Identifiant incorrect!!!!");
        } else {
            utilisateur = utilisateurServiceImplement.getUtilisateurById(response);
            HttpSession session = req.getSession();
            session.setAttribute("user", utilisateur.get()); // plus la peine de renvoyer tout l'objet à mon avis. Voici ce que je fais en bas.... pour affichage dans Topbar.jsp

            session.setAttribute("username", utilisateur.get().getPrenom() + " " + utilisateur.get().getNom());
            session.setAttribute("role", utilisateur.get().getRole());

            if (utilisateur.get().getRole() == Role.CLIENT) {
                req.setAttribute("pageContent", "accueilClient.jsp");
                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
            } else {
//                req.setAttribute("pageContent", "dashboard.jsp");
//                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
                  req.getRequestDispatcher("/WEB-INF/view/pages/dashboard.jsp").forward(req, resp);
            }
        }


    }
}
