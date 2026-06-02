package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.*;
import main.java.model.ServiceImplemente.*;
import main.java.model.dao.*;

@WebServlet("/addRecommandation")
public class RecommandationController extends HttpServlet{

    private LocaliteServiceImplemente localiteServiceImplemente;
    private DomaineImplement domaineImplement;
    private CompetenceServiceImplement competenceServiceImplement;
    

    public void init(){
        ProjetDao projetDao = new ProjetDaoImpl();
        CompetenceProjetDao cpd = new CompetenceProjetDaoImplement();
        ClientCompetenceDao ccd = new ClientCompetenceDaoImplement();
        RecommendationImpl reco = new RecommendationImpl(projetDao, cpd, ccd);


        LocaliteDao localiteDao= new LocaliteDaoImplement();
        DomaineDao domaineDao= new DomaineDaoImplement();
        CompetenceDao competenceDao= new CompetenceDaoImplement();
        localiteServiceImplemente= new LocaliteServiceImplemente(localiteDao);
        domaineImplement= new DomaineImplement(domaineDao);
        competenceServiceImplement= new CompetenceServiceImplement(competenceDao);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Localite> localites = localiteServiceImplemente.getAll();
        List<Domaine> domaines = domaineImplement.afficher();
        List<Competence> competences = competenceServiceImplement.afficher();
        req.setAttribute("localites", localites);
        req.setAttribute("domaines", domaines);
        req.setAttribute("competences", competences);
        req.setAttribute("pageContent", "formRecomm.jsp");
        req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        try {
            String competence = request.getParameter("competence");
            String niveau = Niveau.valueOf(request.getParameter("niveau"));
            int budget = Integer.parseInt(request.getParameter("budget"));
            int idLocalite = Integer.parseInt(request.getParameter("idLocalite"));
            int idDomaine = Integer.parseInt(request.getParameter("idDomaine"));

            Localite localite = new Localite(); localite.setId(idLocalite);
            Domaine domaine = new Domaine(); domaine.setId(idDomaine);

            Utilisateur client = VerifySession.verifyUser(request, response);
            boolean updateSuccess = addInfoClient(client.getIdUtilisateur, niveau, localite, domaine, budget);

            if (updateSuccess) {
                List<Projet> recommandations = reco.suggererProjets(client);
                request.setAttribute("recommandations", recommandations);
                request.getRequestDispatcher("/WEB-INF/view/pages/recommandations.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Données incorrectes - Client non mis à jour");
                request.setAttribute("pageContent", "formRecomm.jsp");
                request.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").include(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Un problème est survenu : " + e.getMessage());
            request.setAttribute("pageContent", "formRecomm.jsp");
            request.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").include(request, response);
        }
    }

    
}