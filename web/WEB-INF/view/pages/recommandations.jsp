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
    List<Projet> recos = (List<Projet>) request.getAttribute("recommandations");
%>

<div class="detail-projet-container">

    <div class="section-title">

        <h2>
            Quelques recos
        </h2>

    </div>

    <div class="etapes-container">

        <% for(Projet projet : recos){ %>

        <a class="etape-card"

           href="#">

            <div class="numero">

                x

            </div>

            <h3>

                <%= projet.getTitre() %>

            </h3>

            <p>

                <%= projet.getDescription() %>

            </p>

        </a>

        <% } %>

    </div>

</div>

</body>
</html>
