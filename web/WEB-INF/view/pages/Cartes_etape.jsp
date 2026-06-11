<%--
  Created by IntelliJ IDEA.
  User: kalandew12
  Date: 04/06/2026
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.*" %>
<%@ page import="main.java.model.enums.StatutEtape" %>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cartes_etape.css">
    <link href="https://googleapis.com" rel="stylesheet">
</head>
<body>

<%
    Projet projet = (Projet) request.getAttribute("projet");
    List<SuivieEtape> suivis = (List<SuivieEtape>) request.getAttribute("suivis");
%>

<div class="detail-projet-container">

    <div class="project-header">
        <h1><%= projet.getTitre() %></h1>
        <p class="description"><%= projet.getDescription() %></p>
    </div>

    <div class="project-infos">
        <div class="info-card">
            <span>Domaine</span>
            <strong><%= projet.getDomaine().getDomaine() %></strong>
        </div>

        <div class="info-card">
            <span>Durée</span>
            <strong><%= projet.getDuree() %> mois</strong>
        </div>

        <div class="info-card">
            <span>Budget estimé</span>
            <strong><%= projet.getBudgetMax() %> FCFA</strong>
        </div>
    </div>

    <div class="section-title">
        <h2>Les étapes du projet</h2>
    </div>

    <div class="timeline-etapes">
        <%
            if (suivis != null && !suivis.isEmpty()) {
                boolean canAccessCurrent = true;

                for(int i = 0; i < suivis.size(); i++) {
                    SuivieEtape suiviActuel = suivis.get(i);
                    Etape etape = suiviActuel.getEtape();
                    StatutEtape statutActuel = suiviActuel.getStatutEtape();

                    if (i > 0) {
                        SuivieEtape suiviPrecedent = suivis.get(i - 1);
                        canAccessCurrent = StatutEtape.TERMINE.equals(suiviPrecedent.getStatutEtape());
                    }
        %>

        <div class="etape-card <%= canAccessCurrent ? "" : "locked" %>">

            <div class="etape-header">
                <div class="etape-ordre">Étape <%= etape.getOrdre() %></div>
                <% if (!canAccessCurrent) { %>
                <div class="lock-icon">
                    <span class="material-icons">lock</span>
                </div>
                <% } %>
            </div>

            <div class="etape-corps">
                <% if (canAccessCurrent) { %>
                <!-- Contenu si l'étape est déverrouillée -->
                <h3><%= etape.getTitre() %></h3>
                <p><%= etape.getDescription() %></p>
                <span class="badge status-<%= statutActuel.toString().toLowerCase() %>">
                        <%= statutActuel.toString() %>
                    </span>
                <% } else { %>
                <!-- Masqué : aucun texte généré pour les étapes verrouillées -->
                <% } %>
            </div>

            <div class="etape-actions">
                <% if (canAccessCurrent) { %>
                <a href="${pageContext.request.contextPath}/etape_activite?idEtape=<%= etape.getIdEtape() %>&titreEtape=<%= etape.getTitre() %>&idProjet=<%= projet.getId() %>" class="btn-action">
                    Voir les activités
                </a>
                <% } else { %>
                <button class="btn-action disabled" disabled>
                    Verrouillé
                </button>
                <% } %>
            </div>


        </div>

        <%
            }
        } else {
        %>
        <p class="no-data">Aucune étape disponible pour ce projet.</p>
        <% } %>
    </div>

</div>

</body>
