<%--
  Created by IntelliJ IDEA.
  User: kalandew12
  Date: 01/06/2026
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Associer Compétence au Projet</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/stylee.css">
</head>
<body>

<h1>Nouvelle association Compétence - Projet</h1>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="error-msg"><%= error %></div>
<% } %>

<form action="competenceProjet?action=enregistrerAjout" method="post" style="max-width: 500px;">
    <div class="form-group">
        <label for="competenceId">ID de la Compétence :</label>
        <input type="number" id="competenceId" name="competenceId" min="1" required>
    </div>

    <div class="form-group">
        <label for="projetId">ID du Projet :</label>
        <input type="number" id="projetId" name="projetId" min="1" required>
    </div>

    <div style="margin-top: 20px;">
        <button type="submit" class="btn">Enregistrer l'association</button>
        <a href="competenceProjet" class="btn" style="background-color: #6c757d;">Annuler</a>
    </div>
</form>

</body>
</html>

