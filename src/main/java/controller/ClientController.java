package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.model.DaoImplement.ClientDAOImplement;
import main.java.model.DaoImplement.EtapeDaoImplement;
import main.java.model.DaoImplement.ProjetClientDAOImplement;
import main.java.model.ServiceImplemente.ClientServiceImplement;
import main.java.model.ServiceImplemente.EtapeServiceImplement;
import main.java.model.ServiceImplemente.ProjetClientServiceImplement;
import main.java.model.classes.Client;
import main.java.model.classes.ProjetClient;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.ClientDAO;
import main.java.model.dao.EtapeDao;
import main.java.model.dao.ProjetClientDAO;
import main.java.model.service.ClientService;
import main.java.model.service.DepenseService;
import main.java.model.dao.DepenseDao;
import main.java.model.DaoImplement.DepenseDaoImplement;
import main.java.model.ServiceImplemente.DepenseImplement;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@WebServlet("/client")
public class ClientController extends HttpServlet {

    private ClientServiceImplement clientServiceImplement;
    private ProjetClientServiceImplement projetClientServiceImplement;
    private EtapeServiceImplement etapeServiceImplement;
    private DepenseService depenseService;


    public void init(){
        ClientDAO clientDAO= new ClientDAOImplement();
        ProjetClientDAO  projetClientDAO= new ProjetClientDAOImplement();
        EtapeDao etapeDao= new EtapeDaoImplement();
        clientServiceImplement= new ClientServiceImplement(clientDAO);
        projetClientServiceImplement= new ProjetClientServiceImplement(projetClientDAO);
        etapeServiceImplement= new EtapeServiceImplement(etapeDao);
        DepenseDao depenseDao = new DepenseDaoImplement();
        depenseService = new DepenseImplement(depenseDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom= req.getParameter("nom");
        String prenom= req.getParameter("prenom");
        String email= req.getParameter("email");
        String telephone= req.getParameter("telephone");
        String password= req.getParameter("password");
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setMotDePasse(password);
        boolean response= clientServiceImplement.addClient(client);
        if(!response){
            req.setAttribute("erreur", "Cet utilisateur existe deja !!!");
            req.getRequestDispatcher("/WEB-INF/view/pages/inscription.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("succes", "Connectez-vous avec vos identifiants !!!");
        req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req, resp);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
       // List<Client> clients = clientService.getAllClients();
        HttpSession session= req.getSession();
        Utilisateur user= (Utilisateur) session.getAttribute("user");
        if(user==null){
            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req, resp);
            return;
        }

        Optional<ProjetClient> projetClientOpt= projetClientServiceImplement.getByClientEncours(user.getIdUtilisateur());
        projetClientOpt.ifPresent(projetClient -> {
            req.setAttribute("projetClientOpt", projetClient);
            int nbreEtape= etapeServiceImplement.countEtapes(projetClient.getProjet().getId());
            req.setAttribute("nbreEtape", nbreEtape);
            //envoie des depense du client sur le projet qu'il accuellement
            double totaldepenseparprojet=projetClientServiceImplement.getProjetDepenseEnCours(user.getIdUtilisateur());
            req.setAttribute("totaldepenseparprojet",  new DecimalFormat("#, ###").format(totaldepenseparprojet).replace(",", " "));


            double totalDepense =
                    depenseService.getTotalDepenseClient(user.getIdUtilisateur());

            req.setAttribute("totalDepense",  new DecimalFormat("#, ###").format(totalDepense).replace(",", " "));

        });

        req.setAttribute("pageContent", "accueilClient.jsp");
        req.setAttribute("menuActif", "accueil");
        req.getRequestDispatcher("/WEB-INF/view/layouts/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
