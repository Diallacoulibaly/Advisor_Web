package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.DaoImplement.SuivieEtapeDaoImplement;
import main.java.model.ServiceImplemente.SuivieEtapeServiceImplement;
import main.java.model.classes.Utilisateur;
import main.java.model.service.SuivieEtapeService;
import main.java.utils.VerifySession;

import java.io.IOException;

@WebServlet("/validerEtape")
public class ValiderEtapeServlet extends HttpServlet {

    private SuivieEtapeService suivieEtapeService = new SuivieEtapeServiceImplement(new SuivieEtapeDaoImplement());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Utilisateur clientConnecte = VerifySession.verifyUser(request, response);

            if (clientConnecte == null) {
                return;
            }

            int idEtape = Integer.parseInt(request.getParameter("idEtape"));
            int idProjet = Integer.parseInt(request.getParameter("idProjet"));
            int idClient = clientConnecte.getIdUtilisateur();

            suivieEtapeService.validerEtapeEtOuvrirSuivante(idEtape, idClient);

            response.sendRedirect(request.getContextPath() + "/projetDetail?id=" + idProjet);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres invalides.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
