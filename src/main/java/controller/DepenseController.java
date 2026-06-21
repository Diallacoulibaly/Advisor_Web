package main.java.controller;

import main.java.model.DaoImplement.ActiviteClientDaoImplement;
import main.java.model.ServiceImplemente.ActiviteClientServiceImplement;
import main.java.model.classes.Activite;
import main.java.model.classes.Client;
import main.java.model.classes.Depense;
import main.java.model.ServiceImplemente.DepenseImplement;
import main.java.model.classes.Utilisateur;
import main.java.model.dao.DepenseDao;
import main.java.model.dao.ActiviteCloentDao;
import main.java.model.DaoImplement.DepenseDaoImplement;
import main.java.model.service.DepenseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.utils.VerifySession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet("/depenses")
public class DepenseController extends HttpServlet {

    private DepenseService depenseService;
    private ActiviteClientServiceImplement activiteClientServiceImplement;

    @Override
    public void init() {
        // CORRECTION: instanciation correcte du DAO et du service
        DepenseDao depenseDao = new DepenseDaoImplement();
        depenseService = new DepenseImplement(depenseDao);

        ActiviteCloentDao activiteClientDao = new ActiviteClientDaoImplement();
        activiteClientServiceImplement = new ActiviteClientServiceImplement(activiteClientDao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "liste";

        switch (action) {
            case "liste" -> {

                String idActiviteParam = request.getParameter("idActivite");


                int idActivite = Integer.parseInt(idActiviteParam);

                List<Depense> depenses = depenseService.getDepenseByActivite(idActivite);

                request.setAttribute("depenses", depenses);
                request.setAttribute("idActivite", idActivite);

                request.getRequestDispatcher("/WEB-INF/view/liste.jsp")
                        .forward(request, response);
            }
            case "supprimer" -> {
                String idParam = request.getParameter("idDepense");
                String idActivite = request.getParameter("idActivite");

                if (idParam != null && !idParam.isEmpty()) {
                    depenseService.delete(Integer.parseInt(idParam));
                }

                response.sendRedirect(request.getContextPath()
                        + "/depenses?action=liste&idActivite=" + idActivite);
            }
            case "formulaire" -> {
                String idParam = request.getParameter("id");
                if (idParam != null && !idParam.isEmpty()) {
                    Optional<Depense> depense = depenseService.getById(Integer.parseInt(idParam));
                    depense.ifPresent(d -> request.setAttribute("depense", d));
                }
                request.getRequestDispatcher("/WEB-INF/views/depenses/form.jsp").forward(request, response);
            }
            default -> response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Utilisateur user = VerifySession.verifyUser(request, response);
        String action = request.getParameter("action");
        String idEtape = request.getParameter("idEtape");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/depenses?action=liste");
            return;
        }

        switch (action) {
            case "ajouter" -> {

                try {
                    double montant = Double.parseDouble(request.getParameter("montant"));
                    String description = request.getParameter("description");
                    //jour actuelle
                    Date date = new Date(System.currentTimeMillis());

                    String idAct = request.getParameter("idActivite");

                    Activite activite = new Activite();
                    activite.setId(Integer.parseInt(idAct));

                    Client client = new Client();
                    if (user != null) {
                        client.setIdUtilisateur(user.getIdUtilisateur());
                    }

                    Depense depense = new Depense(null, montant, description, date, activite, client);

                    depenseService.add(depense);

                } catch (Exception e) {

                    request.setAttribute("erreur", e.getMessage());
                    request.getRequestDispatcher("/WEB-INF/view/pages/activite.jsp")
                            .forward(request, response);
                    return;
                }
            }

            case "modifier" -> {

                try {
                    int idDepense = Integer.parseInt(request.getParameter("idDepense"));
                    double montant = Double.parseDouble(request.getParameter("montant"));
                    String description = request.getParameter("description");
                    Date date = new Date(System.currentTimeMillis());

                    String idAct = request.getParameter("idActivite");

                    Activite activite = new Activite();
                    activite.setId(Integer.parseInt(idAct));

                    Client client = new Client();
                    if (user != null) {
                        client.setIdUtilisateur(user.getIdUtilisateur());
                    }

                    Depense depense = new Depense(
                            idDepense,
                            montant,
                            description,
                            date,
                            activite,
                            client
                    );

                    depenseService.update(depense);

                } catch (Exception e) {

                    request.setAttribute("erreur", e.getMessage());
                    request.getRequestDispatcher("/WEB-INF/view/pages/activite.jsp")
                            .forward(request, response);
                    return;
                }
            }
        }


        response.sendRedirect(request.getContextPath()
                + "/etape_activite?idEtape=" + idEtape);
    }
}