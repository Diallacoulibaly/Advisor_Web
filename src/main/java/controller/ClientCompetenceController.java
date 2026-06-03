package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.*;
import main.java.model.ServiceImplemente.ClientCompetenceServiceImplement;
import main.java.model.ServiceImplemente.ClientServiceImplement;
import main.java.model.ServiceImplemente.CompetenceServiceImplement;
import main.java.model.classes.Client;
import main.java.model.classes.ClientCompetence;
import main.java.model.classes.Competence;
import main.java.model.dao.*;
import main.java.model.service.ClientCompetenceService;
import main.java.model.service.ClientService;
import main.java.model.service.CompetenceService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/clientCompetences")
public class ClientCompetenceController extends HttpServlet {

    private ClientCompetenceService clientCompetenceService;
    private ClientService clientService;
    private CompetenceService competenceService;

    @Override
    public void init() throws ServletException {

        ClientCompetenceDao clientCompetenceDao = new ClientCompetenceDaoImplement();
        clientCompetenceService = new ClientCompetenceServiceImplement(clientCompetenceDao);

        ClientDAO clientDAO= new ClientDAOImplement();
        clientService = new ClientServiceImplement(clientDAO);

        CompetenceDao competenceDao = new CompetenceDaoImplement();
        competenceService = new CompetenceServiceImplement(competenceDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Client> clients = clientService.getAllClients();
        List<Competence> competences = competenceService.afficher(); // afficher = getAll()

        req.setAttribute("clients", clients);
        req.setAttribute("competences", competences);
        System.out.println("la taille:"+clients.size());

        req.getRequestDispatcher("/WEB-INF/view/pages/ClientCompetence.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("=== doPost appelé ===");

        Integer clientId =
                Integer.parseInt(req.getParameter("clientId"));

        Integer competenceId =
                Integer.parseInt(req.getParameter("competenceId"));

        System.out.println("clientId = " + clientId);
        System.out.println("competenceId = " + competenceId);

        ClientCompetence clientCompetence =
                new ClientCompetence(clientId, competenceId);

        clientCompetenceService.add(clientCompetence);

        System.out.println("Ajout effectué");

        resp.sendRedirect("clientCompetences");
    }
}