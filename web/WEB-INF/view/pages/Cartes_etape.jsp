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
    List<Etape> etapes = (List<Etape>) request.getAttribute("etapes");
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
            <strong><%= projet.getBudgetMaxFormat() %> FCFA</strong>
        </div>
    </div>

    <div class="section-title">
        <h2>Les étapes du projet</h2>
    </div>

    <div class="timeline-etapes">

        <%
            if (etapes != null && !etapes.isEmpty()) {

                for (Etape etape : etapes) {

                    SuivieEtape suiviCourant = null;

                    if (suivis != null) {
                        for (SuivieEtape s : suivis) {
                            if (s.getEtape().getIdEtape() == etape.getIdEtape()) {
                                suiviCourant = s;
                                break;
                            }
                        }
                    }

                    String statut = "AFAIRE";
                    boolean accessible = false;

                    if (suiviCourant != null) {

                        statut = suiviCourant.getStatutEtape().name();

                        if (suiviCourant.getStatutEtape() == StatutEtape.ENCOURS
                                || suiviCourant.getStatutEtape() == StatutEtape.TERMINE) {

                            accessible = true;
                        }
                    }
        %>

        <div class="etape-card <%= accessible ? "" : "locked" %>">

            <div class="etape-header">

                <div class="etape-ordre">
                    Étape <%= etape.getOrdre() %>
                </div>

                <% if(!accessible){ %>

                <div class="lock-icon">
                    🔒
                </div>

                <% } %>

            </div>

            <div class="etape-corps">

                <% if(accessible){ %>

                <h3><%= etape.getTitre() %></h3>

                <p><%= etape.getDescription() %></p>

                <span class="badge status-<%= statut.toLowerCase() %>">
                <%= statut %>
            </span>

                <% }else{ %>

                <h3>Étape verrouillée</h3>

                <p>
                    Vous devez terminer l'étape précédente avant d'accéder à celle-ci.
                </p>

                <% } %>

            </div>

            <div class="etape-actions">

                <% if(accessible){ %>

                <a href="${pageContext.request.contextPath}/etape_activite?idEtape=<%= etape.getIdEtape() %>&idProjet=<%= projet.getId() %>"
                   class="btn-action">

                    Voir les activités

                </a>

                <% }else{ %>

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

        <p class="no-data">
            Aucune étape disponible pour ce projet.
        </p>

        <%
            }
        %>

    </div>

</div>

</body>
