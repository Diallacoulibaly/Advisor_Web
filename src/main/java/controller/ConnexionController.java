package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.model.DaoImplement.*;
import main.java.model.ServiceImplemente.*;
import main.java.model.classes.ProjetClient;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.*;
import main.java.model.enums.Role;
import main.java.model.service.DepenseService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/connexion")
public class ConnexionController extends HttpServlet {
    private UtilisateurServiceImplement utilisateurServiceImplement;
    private ProjetClientServiceImplement projetClientServiceImplement;
    private EtapeServiceImplement etapeServiceImplement;
    private DepenseService depenseService;


    public void init(){
        EtapeDao etapeDao= new EtapeDaoImplement();
        ProjetClientDAO projetClientDAO= new ProjetClientDAOImplement();

        UtilisateurDao utilisateurDao= new UtilisateurDaoImplement();
        projetClientServiceImplement= new ProjetClientServiceImplement(projetClientDAO);
        etapeServiceImplement= new EtapeServiceImplement(etapeDao);
        utilisateurServiceImplement= new UtilisateurServiceImplement(utilisateurDao);
        DepenseDao depenseDao = new DepenseDaoImplement();
        depenseService = new DepenseImplement(depenseDao);
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
                //envoie des depense du client sur tout les projet qu'il a eu à faire sur l'appli
                int idClient = utilisateur.get().getIdUtilisateur();

                double totalDepense =
                        depenseService.getTotalDepenseClient(idClient);

                req.setAttribute("totalDepense", totalDepense);

                //envoie des depense du client sur le projet qu'il accuellement
                double totaldepenseparprojet=projetClientServiceImplement.getProjetDepenseEnCours(idClient);
                req.setAttribute("totaldepenseparprojet", totaldepenseparprojet);
                Optional<ProjetClient> projetClientOpt= projetClientServiceImplement.getByClientEncours(utilisateur.get().getIdUtilisateur());
                projetClientOpt.ifPresent(projetClient -> {
                    req.setAttribute("projetClientOpt", projetClient);
                    int nbreEtape= etapeServiceImplement.countEtapes(projetClient.getProjet().getId());
                    req.setAttribute("nbreEtape", nbreEtape);

                });
                req.setAttribute("pageContent", "accueilClient.jsp");
                req.setAttribute("menuActif", "accueil");
                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
            } else {
//                req.setAttribute("pageContent", "dashboard.jsp");
//                req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
                  req.getRequestDispatcher("/WEB-INF/view/pages/dashboard.jsp").forward(req, resp);
            }
        }


    }
}
