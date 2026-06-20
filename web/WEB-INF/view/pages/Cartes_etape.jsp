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

                    boolean canAccessCurrent = false;
                    String statutAffiche = "AFAIRE";

                    // Cas où aucun suivi n'existe encore
                    if (suivis == null || suivis.isEmpty()) {

                        canAccessCurrent = (etape.getOrdre() == 1);

                    } else {

                        // Recherche du suivi correspondant à cette étape
                        SuivieEtape suiviCourant = null;

                        for (SuivieEtape s : suivis) {
                            if (s.getEtape().getIdEtape() == etape.getIdEtape()) {
                                suiviCourant = s;
                                break;
                            }
                        }

                        if (suiviCourant != null) {

                            statutAffiche = suiviCourant.getStatutEtape().toString();

                            // Étape 1 toujours accessible
                            if (etape.getOrdre() == 1) {

                                canAccessCurrent = true;

                            } else {

                                // Recherche de l'étape précédente
                                for (SuivieEtape s : suivis) {

                                    if (s.getEtape().getOrdre() == etape.getOrdre() - 1) {

                                        canAccessCurrent =
                                                s.getStatutEtape() == StatutEtape.TERMINE;

                                        break;
                                    }
                                }
                            }

                        } else {

                            // Si pas de suivi trouvé pour cette étape
                            canAccessCurrent = false;
                        }
                    }
        %>

        <div class="etape-card <%= canAccessCurrent ? "" : "locked" %>">

            <div class="etape-header">

                <div class="etape-ordre">
                    Étape <%= etape.getOrdre() %>
                </div>

                <% if (!canAccessCurrent) { %>
                <div class="lock-icon">
                    <span class="material-icons">lock</span>
                </div>
                <% } %>

            </div>

            <div class="etape-corps">

                <% if (canAccessCurrent) { %>

                <h3><%= etape.getTitre() %></h3>

                <p><%= etape.getDescription() %></p>

                <span class="badge status-<%= statutAffiche.toLowerCase() %>">
                    <%= statutAffiche %>
                </span>

                <% } %>

            </div>

            <div class="etape-actions">

                <% if (canAccessCurrent) { %>

                <a href="${pageContext.request.contextPath}/etape_activite?idEtape=<%= etape.getIdEtape() %>&idProjet=<%= projet.getId() %>"
                   class="btn-action">

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

        <%
            }
        %>

    </div>

</div>

</body>
