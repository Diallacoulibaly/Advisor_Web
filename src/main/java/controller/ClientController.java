package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.classes.Client;
import main.java.model.service.ClientService;

import java.io.IOException;
import java.util.List;

@WebServlet("/client")
public class ClientController extends HttpServlet {
//    private ClientService clientService;
//    public ClientController(ClientService clientService) {
//        this.clientService = clientService;
//    }

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
        //clientService.addClient(client);
        resp.sendRedirect("/client");
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
       // List<Client> clients = clientService.getAllClients();
        req.setAttribute("pageContent", "pages/accueilClient.jsp");
        req.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp").forward(req, resp);
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
