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


<html>
<head>
    <title>Mes projets</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mes_projets.css">

</head>
<body>


<%
    List<ProjetClient> projets =
            (List<ProjetClient>) request.getAttribute("projets");
%>

<div class="mes-projets-container">

    <h1>Mes projets</h1>

    <% if(projets == null || projets.isEmpty()) { %>

    <div class="empty-state">

        <h2>Aucun projet trouvé</h2>

        <p>
            Vous n'avez encore lancé aucun projet.
        </p>

    </div>

    <% } else { %>

    <div class="cards-container">

        <% for(ProjetClient projetClient : projets){ %>

        <a class="project-card"
           href="${pageContext.request.contextPath}/projetDetail?id=<%= projetClient.getProjet().getId() %>">

            <div class="card-header">

                <h2>
                    <%= projetClient.getProjet().getTitre() %>
                </h2>

                <span class="status status-<%= projetClient.getStatut().name().toLowerCase() %>">
    <%= projetClient.getStatut() %>
</span>

            </div>

            <div class="card-body">

                <div class="info">

                    <span>Domaine</span>

                    <strong>
                        <%= projetClient.getProjet().getDomaine().getDomaine() %>
                    </strong>

                </div>

                <div class="info">

                    <span>Localité:</span>

                                        <strong>
                                            <%= projetClient.getProjet()
                                                    .getLocalite().getRegionClient() %>
                                        </strong>

                </div>

                <div class="info">

                    <span>Debuté le:</span>

                    <strong>
                        <%= projetClient.getDebut().toLocalDate().toString()%>

                    </strong>

                </div>

                <div class="info">

                    <span>Durée</span>

                    <strong>
                        <%= projetClient.getProjet()
                                .getDuree() %>
                        mois
                    </strong>

                </div>



            </div>

        </a>

        <% } %>

    </div>

    <% } %>

</div>

</body>
</html>
