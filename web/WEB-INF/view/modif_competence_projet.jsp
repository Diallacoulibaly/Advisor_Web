<%--
  Created by IntelliJ IDEA.
  User: kalandew12
  Date: 01/06/2026
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="main.java.model.classes.CompetenceProjet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CompetenceProjet cp = (CompetenceProjet) request.getAttribute("competenceProjet");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier l'Association</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/stylee.css">
</head>
<body>

<h1>Modifier l'association Compétence - Projet</h1>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="error-msg"><%= error %></div>
<% } %>

<% if (cp != null) { %>
<form action="competenceProjet?action=enregistrerModif" method="post" style="max-width: 500px;">

    <!-- Champs cachés pour conserver les anciennes valeurs si votre requête SQL en a besoin -->
    <input type="hidden" name="ancienCompetenceId" value="<%= cp.getCompetenceId() %>">
    <input type="hidden" name="ancienProjetId" value="<%= cp.getIdProjet() %>">

    <div class="form-group">
        <label for="competenceId">Nouvel ID de la Compétence :</label>
        <input type="number" id="competenceId" name="competenceId" value="<%= cp.getCompetenceId() %>" min="1" required>
    </div>

    <div class="form-group">
        <label for="projetId">Nouvel ID du Projet :</label>
        <input type="number" id="projetId" name="projetId" value="<%= cp.getIdProjet() %>" min="1" required>
    </div>

    <div style="margin-top: 20px;">
        <button type="submit" class="btn">Enregistrer les modifications</button>
        <a href="competenceProjet" class="btn" style="background-color: #6c757d;">Annuler</a>
    </div>
</form>
<% } else { %>
<div class="error-msg">Aucune donnée trouvée pour cette association.</div>
<a href="competenceProjet" class="btn">Retour à la liste</a>
<% } %>

</body>
</html>

