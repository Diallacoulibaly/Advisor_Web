package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.ClientDAOImplement;
import main.java.model.ServiceImplemente.ClientServiceImplement;
import main.java.model.classes.Client;
import main.java.model.dao.ClientDAO;

import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionController extends HttpServlet {
    private ClientServiceImplement clientServiceImplement;
    public void init(){
        ClientDAO clientDAO= new ClientDAOImplement();
        clientServiceImplement= new ClientServiceImplement(clientDAO);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/pages/inscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client client= new Client();
        client.setNom(req.getParameter("nom"));
        client.setPrenom(req.getParameter("prenom"));
        client.setEmail(req.getParameter("email"));
        client.setTelephone(req.getParameter("telephone"));
        client.setMotDePasse(req.getParameter("password"));
        boolean response=clientServiceImplement.addClient(client);
        if(response){
            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp");
        }
        else {
            resp.getWriter().println("Erreur lors de l'inscription!!!");
        }
    }
}
