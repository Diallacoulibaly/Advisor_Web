<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25/05/2026
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.java.model.classes.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/accueilClient.css">
    </head>
<body>
<h2>Projet en cours</h2>
<%
    ProjetClient projetClient= (ProjetClient) request.getAttribute("projetClientOpt");
    Double totalDepense = (Double) request.getAttribute("totalDepense");
    if (totalDepense == null) totalDepense = 0.0;
    Double totaldepenseparprojet=(double) request.getAttribute("totaldepenseparprojet");
    if(totaldepenseparprojet==null) totaldepenseparprojet=0.0;
%>
<div class="cards-container">

</div>

    <section class="content">

        <div class="cards-container">
            <% if(projetClient == null) {%>
            <div class="dashboard-card progress-card">

                <h2>Ma progression</h2>

                <div class="card-value">
                    0 <span>%</span>
                </div>

            </div>


            <div class="dashboard-card depense-card">

                <h2>Dépenses faites</h2>

                <div class="money-content">

                    <div class="card-value money">
                        0 F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>


            <div class="dashboard-card preview-card">

                <h2>Dépenses prévu</h2>

                <div class="money-content">

                    <div class="card-value money">
                        0 F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>
            <div class="project-card">
                <div class="project-header">
                    <h2>Pas de projet en cours</h2>
                    <p>Faites une demande de recommandation afin de commencer un projet</p>
                </div>
            </div>

            <% } else { %>


            <!-- CARD 1 -->
            <div class="dashboard-card progress-card">

                <h2>Ma progression</h2>

                <div class="card-value">
                    25 <span>%</span>
                </div>

            </div>


            <div class="dashboard-card depense-card">

                <h2>Dépenses faites</h2>

                <div class="money-content">

                    <div class="card-value money">
                        <%= totalDepense %> F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>


            <div class="dashboard-card preview-card">

                <h2>Dépenses prévu</h2>

                <div class="money-content">

                    <div class="card-value money">
                        <%= projetClient.getProjet().getBudgetMax() %> F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>
            <div class="dashboard-card depense-card">

                <h2>Dépenses faites au cours de cet projet</h2>

                <div class="money-content">

                    <div class="card-value money">
                        <%=totaldepenseparprojet  %> F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>


        </div>
        <div class="project-card">



            <div class="project-header">

                <div>

                    <h2 class="project-title">
                            <%=projetClient.getProjet().getTitre() %>
                    </h2>

                    <p class="project-description">
                            <%=projetClient.getProjet().getDescription()%>
                    </p>

                </div>

                <div class="project-status">
                        <%= projetClient.getStatut() %>
                </div>

            </div>



            <div class="project-infos">

                <div class="info-box">

                    <h3>Durée</h3>

                    <p><%= projetClient.getProjet().getDuree() %> mois</p>

                </div>

                <div class="info-box">

                    <h3>Étapes</h3>

                    <p>${nbreEtape}</p>

                </div>

                <div class="info-box">

                    <h3>Domaine</h3>

                    <p><%= projetClient.getProjet().getDomaine().getDomaine() %></p>

                </div>

                <div class="info-box">

                    <h3>Budget</h3>

                    <p><%= projetClient.getProjet().getBudgetMax() %> F CFA</p>

                </div>

            </div>

        </div>
        <% } %>

    </section>

</body>
</html>
