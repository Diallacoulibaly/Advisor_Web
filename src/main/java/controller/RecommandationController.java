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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addRecommandation")
public class RecommandationController extends HttpServlet{
    private RecommandationImpl reco;
    private LocaliteServiceImplemente localiteServiceImplemente;
    private DomaineImplement domaineImplement;
    private CompetenceServiceImplement competenceServiceImplement;
    private ClientServiceImplement clientServiceImplement;
    private ClientCompetenceServiceImplement clientCompetenceServiceImplement;
    private HistoriqueServiceImplement historiqueServiceImplement;
    private HistoriqueProjetServiceImplement historiqueProjetServiceImplement;
    private EtapeServiceImplement etapeServiceImplement;
    private ProjetClientServiceImplement projetClientServiceImplement;

    public void init(){
        ProjetDao projetDao = new ProjetDaoImpl();
        CompetenceProjetDao cpd = new CompetenceProjetDaoImplement();
        ClientCompetenceDao ccd = new ClientCompetenceDaoImplement();
        EtapeDao etapeDao= new EtapeDaoImplement();
        reco = new RecommandationImpl(projetDao, cpd, ccd);

        ClientDAO clientDAO = new ClientDAOImplement();
        clientServiceImplement = new ClientServiceImplement(clientDAO);

        HistoriqueDao historiqueDao = new HistoriqueDaoImplement();
        historiqueServiceImplement = new HistoriqueServiceImplement(historiqueDao);

        HistoriqueProjetDao hpd = new HistoriqueProjetDaoImplement();
        historiqueProjetServiceImplement = new HistoriqueProjetServiceImplement(hpd);

        ProjetClientDAO pcd = new ProjetClientDAOImplement();
        projetClientServiceImplement = new ProjetClientServiceImplement(pcd);

        LocaliteDao localiteDao= new LocaliteDaoImplement();
        DomaineDao domaineDao= new DomaineDaoImplement();
        CompetenceDao competenceDao= new CompetenceDaoImplement();
        localiteServiceImplemente= new LocaliteServiceImplemente(localiteDao);
        domaineImplement= new DomaineImplement(domaineDao);
        competenceServiceImplement= new CompetenceServiceImplement(competenceDao);
        clientCompetenceServiceImplement= new ClientCompetenceServiceImplement(ccd);
        etapeServiceImplement= new EtapeServiceImplement(etapeDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Localite> localites = localiteServiceImplemente.getAll();
        List<Domaine> domaines = domaineImplement.afficher();
        List<Competence> competences = competenceServiceImplement.afficher();
        req.setAttribute("localites", localites);
        req.setAttribute("domaines", domaines);
        req.setAttribute("competences", competences);
        req.setAttribute("menuActif", "recommandation");
        req.setAttribute("pageContent", "formRecomm.jsp");

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



            Localite localite = localiteServiceImplemente.getById(idLocalite);
            Domaine domaine = domaineImplement.getById(idDomaine);


            boolean updateSuccess = clientServiceImplement.addInfoClient(user.getIdUtilisateur(), niveau, localite, domaine, budget);

            if (updateSuccess) {
                Client client = new Client();
                client.setBudgetApporte(budget);
                client.setIdUtilisateur(user.getIdUtilisateur());
                client.setLocalite(localite);
                client.setDomaine(domaine);
                client.setNiveau(niveau);

                List<Projet> recommandations = reco.suggererProjets(client);
                List<ProjetClient> projetsClient = projetClientServiceImplement.getByClient(user.getIdUtilisateur());

                for (ProjetClient pc : projetsClient) {
                    recommandations.removeIf(p -> pc.getProjet().getId() == p.getId());
                }


                if (!recommandations.isEmpty()) {
                    Historique historique = new Historique();
                    historique.setIdClient(client.getIdUtilisateur());
                    int idHist = historiqueServiceImplement.ajouterHistorique(historique);
                    historique.setId(idHist);

                    Map<Integer, Integer> nbEtapesMap = new HashMap<>();
                    for (Projet p : recommandations) {
                        nbEtapesMap.put(p.getId(), etapeServiceImplement.countEtapes(p.getId()));
                        HistoriqueProjet hp = new HistoriqueProjet(historique, p);
                        historiqueProjetServiceImplement.add(hp);

                    }
                    request.setAttribute("NbreEtapes", nbEtapesMap);



                }
                
                request.setAttribute("recommandations", recommandations);

                request.setAttribute("pageContent", "recommandations.jsp");
                request.setAttribute("menuActif", "recommandation");

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