<%@ page import="main.java.model.enums.StatutEtape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter une Étape</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/stylee.css">
</head>
<body>

<h1>Ajouter une nouvelle Étape</h1>

<%-- Affichage des messages d'erreur --%>
<% String error = (String) request.getAttribute("error");
    if (error != null) { %>
<div class="error-msg"><%= error %></div>
<% } %>

<form action="etape?action=Ajout" method="post" style="max-width: 500px;">
    <div class="form-group">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" required>
    </div>

    <div class="form-group">
        <label for="description">Description :</label>
        <textarea id="description" name="description" rows="4" required></textarea>
    </div>

    <div class="form-group">
        <label for="ordre">Ordre :</label>
        <input type="number" id="ordre" name="ordre" min="1" required>
    </div>

    <div class="form-group">
        <label for="statut">Statut :</label>
        <select id="statut" name="statut" required>
            <% for (StatutEtape statut : StatutEtape.values()) { %>
            <option value="<%= statut.name() %>"><%= statut.name() %></option>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <label for="projetId">ID du Projet :</label>
        <input type="number" id="projetId" name="projetId" required>
    </div>

    <div style="margin-top: 20px;">
        <button type="submit" class="btn">Enregistrer</button>
        <a href="etape" class="btn" style="background-color: #6c757d;">Annuler</a>
    </div>
</form>

</body>
</html>
