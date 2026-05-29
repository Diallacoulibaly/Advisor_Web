<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--c'est toi qui as ajouté cette ligne Ismailou ? :|    vraiment???????--%>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Connexion</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/connexion.css">


</head>

<body>

    <div class="container">

        <div class="right-section">

            <div class="welcome-content">

                <h2>Bienvenue sur Advisor</h2>

                <p>
                    Inscrivez-vous dès maintenant et profitez de notre site
                </p>
                <a href="inscription">
                    <button class="btn-connexion">
                        Inscription
                    </button>
                </a>


            </div>

        </div>

        <div class="left-section">

            <h1>Se connecter</h1>

            <form action="connexion" method="post">


                <div class="input-group">
                    <input type="email" placeholder="Entre votre email" name="email">
                </div>

                <div class="input-group password-group">

                    <input type="password" placeholder="Mot de passe" name="password">

                    <span class="eye-icon">👁</span>

                </div>

                <button type="submit" class="btn-inscription">
                    Connexion
                </button>

                <% if (request.getAttribute("erreur")!= null) {%>
                <h3 style="color: red; font-weight: bold">${erreur}</h3>
                    <%
        }
    %>


            </form>

        </div>





    </div>

</body>

</html>