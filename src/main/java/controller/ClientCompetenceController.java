//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import main.java.model.classes.Client;
//import main.java.model.dao.ClientCompetence;
//import main.java.model.service.ClientCompetenceService;
//import main.java.model.service.ClientService;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/clientCompetences")
//public class ClientCompetenceController extends HttpServlet {
//
//    private ClientCompetenceService clientCompetenceService;
//    private ClientService clientService;
//    private CompetenceService competenceService;
//
//    @Override
//    public void init() throws ServletException {
//        clientCompetenceService = new ClientCompetenceServiceImpl();
//        clientService = new ClientServiceImpl();
//        competenceService = new CompetenceServiceImpl();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        List<Client> clients = clientService.getAll();
//        List<Competence> competences = competenceService.getAll();
//
//        req.setAttribute("clients", clients);
//        req.setAttribute("competences", competences);
//
//        req.getRequestDispatcher("/WEB-INF/view/clientCompetence/form.jsp")
//                .forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        Integer clientId =
//                Integer.parseInt(req.getParameter("clientId"));
//
//        Integer competenceId =
//                Integer.parseInt(req.getParameter("competenceId"));
//
//        Client client = clientService.getById(clientId);
//        Competence competence =
//                competenceService.getById(competenceId);
//
//        ClientCompetence clientCompetence =
//                new ClientCompetence(null, competence, client);
//
//        clientCompetenceService.add(clientCompetence);
//
//        resp.sendRedirect("clientCompetences");
//    }
//}