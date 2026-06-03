package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.*;
import main.java.model.ServiceImplemente.*;
import main.java.model.classes.*;
import main.java.model.dao.*;
import main.java.model.enums.Niveau;

import main.java.utils.VerifySession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addRecommandation")
public class RecommandationController extends HttpServlet{
    private RecommandationImpl reco;
    private LocaliteServiceImplemente localiteServiceImplemente;
    private DomaineImplement domaineImplement;
    private CompetenceServiceImplement competenceServiceImplement;
    private ClientServiceImplement clientServiceImplement;
    private ClientCompetenceServiceImplement clientCompetenceServiceImplement;
    

    public void init(){
        ProjetDao projetDao = new ProjetDaoImpl();
        CompetenceProjetDao cpd = new CompetenceProjetDaoImplement();
        ClientCompetenceDao ccd = new ClientCompetenceDaoImplement();
        reco = new RecommandationImpl(projetDao, cpd, ccd);
        ClientCompetenceDao clientCompetenceDao= new ClientCompetenceDaoImplement();
        ClientDAO clientDAO = new ClientDAOImplement();
        clientServiceImplement = new ClientServiceImplement(clientDAO);


        LocaliteDao localiteDao= new LocaliteDaoImplement();
        DomaineDao domaineDao= new DomaineDaoImplement();
        CompetenceDao competenceDao= new CompetenceDaoImplement();
        localiteServiceImplemente= new LocaliteServiceImplemente(localiteDao);
        domaineImplement= new DomaineImplement(domaineDao);
        competenceServiceImplement= new CompetenceServiceImplement(competenceDao);
        clientCompetenceServiceImplement= new ClientCompetenceServiceImplement(clientCompetenceDao);

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
        req.setAttribute("menuActif", "recommandation");
        req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
    }


    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        try {

            Utilisateur user = VerifySession.verifyUser(request, response);
            Niveau niveau = Niveau.valueOf(request.getParameter("niveau"));
            int budget = Integer.parseInt(request.getParameter("budget"));
            int idLocalite = Integer.parseInt(request.getParameter("idLocalite"));
            int idDomaine = Integer.parseInt(request.getParameter("idDomaine"));
            String [] competences= request.getParameterValues("competences");
            List<Integer> competencesId= new ArrayList<>();

            for(String id : competences){
                competencesId.add((Integer.parseInt(id)));
            }

            clientCompetenceServiceImplement.addListClientCompetence(clientCompetenceServiceImplement.filterSkills(competencesId, user.getIdUtilisateur()), user.getIdUtilisateur());



            Localite localite = new Localite();
            localite = localiteServiceImplemente.getById(idLocalite);
            Domaine domaine = new Domaine();
            domaine = domaineImplement.getById(idDomaine);


            boolean updateSuccess = clientServiceImplement.addInfoClient(user.getIdUtilisateur(), niveau, localite, domaine, budget);

            if (updateSuccess) {
                Client client = new Client();
                client.setBudgetApporte(budget);
                client.setIdUtilisateur(user.getIdUtilisateur());
                client.setLocalite(localite);
                client.setDomaine(domaine);


                List<Projet> recommandations = reco.suggererProjets(client);
                request.setAttribute("recommandations", recommandations);
                request.setAttribute("pageContent", "recommandations.jsp");
                request.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Données incorrectes - Client non mis à jour");
                request.setAttribute("pageContent", "formRecomm.jsp");
                request.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Un problème est survenu : " + e.getMessage());
            request.setAttribute("pageContent", "formRecomm.jsp");
            request.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(request, response);
        }
    }

    
}