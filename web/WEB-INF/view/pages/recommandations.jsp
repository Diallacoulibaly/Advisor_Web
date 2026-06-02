<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 31/05/2026
  Time: 01:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.*" %>
<html>
<head>
    <title>Recommandations</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detailProjet.css">

</head>
<body>


<%
    List<Projet> recos = (List<Projet>) request.getAttribute("recommandations);
%>

<div class="detail-projet-container">

    <div class="project-header">

        <h1>
            <%= projet.getTitre() %>
        </h1>

        <p class="description">
            <%= projet.getDescription() %>
        </p>

    </div>

    <div class="project-infos">

        <div class="info-card">

            <span>Domaine</span>

            <strong>
                <%= projet.getDomaine()
                        .getDomaine() %>
            </strong>

        </div>

        <div class="info-card">

            <span>Durée</span>

            <strong>
                <%= projet.getDuree() %>
                mois
            </strong>

        </div>

        <div class="info-card">

            <span>Budget estimé</span>

            <strong>
                <%= projet.getBudgetMax() %>
                FCFA
            </strong>

        </div>

    </div>

    <div class="section-title">

        <h2>
            Quelques recos
        </h2>

    </div>

    <div class="etapes-container">

        <% for(Projet projet : recos){ %>

        <a class="etape-card"

           href="activitesEtape?idEtape=<%= projet.getIdEtap() %>">

            <div class="numero">

                Étape <%= etape.getOrdre() %>

            </div>

            <h3>

                <%= etape.getTitre() %>

            </h3>

            <p>

                <%= etape.getDescription() %>

            </p>

        </a>

        <% } %>

    </div>

</div>

</body>
</html>
