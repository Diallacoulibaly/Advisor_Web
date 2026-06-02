package main.java.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.model.classes.Utilisateur;

import java.io.IOException;


public class VerifySession {

    public static Utilisateur verifyUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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