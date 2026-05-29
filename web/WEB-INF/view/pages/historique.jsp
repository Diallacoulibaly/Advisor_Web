<%@ page import="main.java.model.classes.Historique" %>
<%@ page import="java.util.List" %><%--
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
    <% List<Historique> historiqueList = (List<Historique>) request.getAttribute("historiqueList"); %>

    <h1>Liste des historiques de projets </h1>
    <ol>
        <% for (Historique historique : historiqueList ){%>
            <li> <%= historique.getDescriptionAction()%> | <%= historique.getDate()%></li>
        <% } %>
    </ol>
</body>
</html>
