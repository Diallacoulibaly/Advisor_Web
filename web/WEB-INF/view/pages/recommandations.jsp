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
    <title>Projets recommandés</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/mes_projets.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detailProjet.css">

</head>
<body>


<%
    List<Projet> projets = (List<Projet>) request.getAttribute("recommandations");
    
%>

<div class="mes-projets-container">

    <h1>Ces projets pourraient vous intéresser</h1>

    <% if(projets == null || projets.isEmpty()) { %>

    <div class="empty-state">

        <h2>Aucun projet ne correspond à vos critères</h2>

        <p>
            Veuillez réajuster vos critères .
        </p>

    </div>
    <% } else { %>

    <div class="cards-container">

        <% for(Projet projet : projets){ %>

        <div class="project-card">

            <div class="card-header">
                <h2><%= projet.getTitre() %></h2>

                <form action="mes_projets" method="post">
                    <input type="hidden" name="idProjet" value="<%= projet.getId()%>">
                    <button type="submit" class="status">
                        Lancer ce projet
                    </button>
                </form>
                
            </div>

            <div class="card-body">

                <%= projet.getDescription() %>
            
                <div class="info">
                    <span>Domaine</span>
                    <strong> <%= projet.getDomaine().getDomaine() %> </strong>
                </div>

                <div class="info">
                    <span>Localité</span>
                    <strong> <%= projet.getLocalite().getRegionClient() %> </strong>
                </div>

                <!-- <div class="info">
                    <span>Nombre d'étapes</span>
                    <strong> 3 </strong>
                </div> -->

                <div class="info">
                    <span>Durée</span>
                    <strong> <%= projet.getDuree() %> mois </strong>
                </div>

            </div>

        </div>

        <% } %>

    </div>

    <% } %>

</div>

</body>
</html>
