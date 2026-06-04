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
    List<Projet> projets = (List<Projet>) request.getAttribute("recommandations");
    Map<Integer, Integer> nbEtapesMap = (Map<Integer, Integer>) request.getAttribute("NbreEtapes");
    String erreur= (String) request.getAttribute("erreur");


%>

<div class="mes-projets-container">

    <h4  style="margin-bottom: 20px; color:#17253C;">Ces projets pourraient vous intéresser</h4>

    <% if(projets == null || projets.isEmpty()) { %>

    <div class="empty-state">

        <h2>Aucun projet ne correspond à vos critères</h2>

        <p>
            Veuillez réajuster vos critères .
        </p>

    </div>
    <% } else { %>

    <div class="cards-container">

        <% for(Projet projet : projets){
            int nbEt = nbEtapesMap.get(projet.getId());
        %>

        <div class="project-card">

            <div class="project-top">

                <h2 class="project-title">
                    <%= projet.getTitre() %>
                </h2>

                <form action="mes_projets" method="post">

                    <input type="hidden" name="idProjet" value="<%= projet.getId()%>">

                    <button type="submit" class="btn-launch">Lancer ce projet</button>

                </form>

            </div>

            <div class="project-description">

                <%= projet.getDescription() %>

            </div>

            <div class="project-details">

                <div class="detail-row">

                    <span>Domaine</span>

                    <strong>
                        <%= projet.getDomaine().getDomaine() %>
                    </strong>

                </div>

                <div class="detail-row">

                    <span>Localité</span>

                    <strong>
                        <%= projet.getLocalite().getRegionClient() %>
                    </strong>

                </div>

                <div class="detail-row">

                    <span>Durée</span>

                    <strong>
                        <%= projet.getDuree() %> mois
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

    <% if (request.getAttribute("erreur")!= null) {%>
    <h3 style="color: red; font-weight: bold">${erreur}</h3>
    <%
        }
    %>

</div>

</body>
</html>
