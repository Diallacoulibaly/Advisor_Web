<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.CompetenceProjet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<CompetenceProjet> liste = (List<CompetenceProjet>) request.getAttribute("competenceProjets");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Compétences par Projet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/stylee.css">
</head>
<body>

<h1>Associations Compétences - Projets</h1>

<div style="margin-bottom: 20px;">
    <a href="competenceProjet?action=ajouter" class="btn">Associer une compétence</a>
</div>

<table>
    <thead>
    <tr>
        <th>ID Compétence</th>
        <th>ID Projet</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (liste != null && !liste.isEmpty()) {
            for (CompetenceProjet cp : liste) {
    %>
    <tr>
        <td><%= cp.getCompetenceId() %></td>
        <td><%= cp.getIdProjet() %></td>
        <td>
            <a class="btn-edit" href="competenceProjet?action=modifier&competenceId=<%= cp.getCompetenceId() %>&projetId=<%= cp.getIdProjet() %>">Modifier</a>

            <a class="btn-delete" href="competenceProjet?action=supprimer&competenceId=<%= cp.getCompetenceId() %>&projetId=<%= cp.getIdProjet() %>" onclick="confirmerSuppression(event)">Supprimer</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3" style="text-align: center; color: #666; padding: 20px;">Aucune association enregistrée.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</body>
</html>

