<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30/05/2026
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%@ page import="main.java.model.classes.*" %>
<%@ page import="java.util.Map" %>


<html>
<head>
    <title>Projets recommandés</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/recommandation.css">

</head>
<body>


<%

 List<HistoriqueProjet> projets = (List<HistoriqueProjet>) request.getAttribute("historiqueList");

    Map<Integer, Integer> nbEtapesMap = (Map<Integer, Integer>) request.getAttribute("NbreEtapes");
    //String erreur= (String) request.getAttribute("erreur");


%>

<div class="mes-projets-container">



    <% if(projets == null || projets.isEmpty()) { %>

    <div class="empty-state">

        <h2>Aucun projet n'est enregistré dans votre historique</h2>

        <p>
            Veuillez faire une demande de recommandation.
        </p>

    </div>
    <% } else { %>
    <h4  style="margin-bottom: 20px; color:#17253C;">Vos historiques</h4>

    <div class="cards-container">

        <% for(HistoriqueProjet projet : projets){
            int nbEt = nbEtapesMap.get(projet.getProjet().getId());
        %>

        <div class="project-card">

            <div class="project-top">

                <h2 class="project-title">
                    <%= projet.getProjet().getTitre() %>
                </h2>

                <form action="mes_projets" method="post">

                    <input type="hidden" name="idProjet" value="<%= projet.getProjet().getId()%>">

                    <button type="submit" class="btn-launch">Lancer ce projet</button>

                </form>

            </div>

            <div class="project-description">

                <%= projet.getProjet().getDescription() %>

            </div>

            <div class="project-details">

                <div class="detail-row">

                    <span>Domaine</span>

                    <strong>
                        <%= projet.getProjet().getDomaine().getDomaine() %>
                    </strong>

                </div>

                <div class="detail-row">

                    <span>Localité</span>

                    <strong>
                        <%= projet.getProjet().getLocalite().getRegionClient() %>
                    </strong>

                </div>

                <div class="detail-row">

                    <span>Durée</span>

                    <strong>
                        <%= projet.getProjet().getDuree() %> mois
                    </strong>

                </div>
                <div class="detail-row">

                    <span>Nombre d'étapes</span>

                    <strong>
                        <%= nbEt %>
                    </strong>

                </div>

            </div>

        </div>

        <% } %>

    </div>

    <% } %>



</div>

</body>
</html>
