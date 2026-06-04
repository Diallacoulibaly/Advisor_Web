<%@ page import="main.java.model.classes.Historique" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.HistoriqueProjet" %><%--
  Created by IntelliJ IDEA.
  User: Cute Boy
  Date: 29/05/2026
  Time: 03:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Historique</title>
</head>
<body>
    <% List<HistoriqueProjet> historiqueList = (List<HistoriqueProjet>) request.getAttribute("historiqueList"); %>

    <h1>Liste des historiques de projets </h1>
    <ol>
        <% for (HistoriqueProjet historique : historiqueList ){%>
            <li> <%= historique.getProjet().getTitre()%> |<%= historique.getProjet().getDescription()%> | <%= historique.getProjet().getDuree()%> | <%= historique.getProjet().getBudgetMin()%> | <%= historique.getProjet().getBudgetMax()%> | <%= historique.getHistorique().getDate()%></li>
        <% } %>
    </ol>
</body>
</html>
