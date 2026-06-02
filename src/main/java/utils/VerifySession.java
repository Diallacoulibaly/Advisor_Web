
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.classes.Utilisateur;


public class VerifySession {

    public static Utilisateur verifyUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         Utilisateur utilisateur =
                (Utilisateur) req.getSession()
                        .getAttribute("user");

        if(utilisateur == null){

            req.getRequestDispatcher("/WEB-INF/view/pages/connexion.jsp").forward(req,resp);
            return null;
        }
        return utilisateur;
    }
}