<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.java.model.classes.Etape" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier une Étape</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .form-group { margin-bottom: 15px; width: 400px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, textarea, select { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        button { padding: 10px 15px; background-color: #28a745; color: white; border: none; cursor: pointer; border-radius: 4px; }
        .btn-cancel { padding: 10px 15px; background-color: #6c757d; color: white; text-decoration: none; margin-left: 10px; border-radius: 4px; display: inline-block; }
        .error { color: #dc3545; font-weight: bold; margin-bottom: 15px; }
    </style>
</head>
<body>

<h2>Modifier l'étape</h2>

<%
    String error = (String) request.getAttribute("error");
    Etape etape = (Etape) request.getAttribute("etape");
    if (error != null) {
%>
<p class="error"><%= error %></p>
<% } %>

<% if (etape != null) { %>
<form action="${pageContext.request.contextPath}/etape" method="post">

    <!-- Transmission du paramètre action requis par le doPost de la servlet -->
    <input type="hidden" name="action" value="enregistrerModif">

    <!-- Transmission de la clé primaire id récupérée via getIdEtape() -->
    <input type="hidden" name="idEtape" value="<%= etape.getIdEtape() %>">

    <div class="form-group">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" value="<%= etape.getTitre() != null ? etape.getTitre() : "" %>" required>
    </div>

    <div class="form-group">
        <label for="description">Description :</label>
        <textarea id="description" name="description" rows="4" required><%= etape.getDescription() != null ? etape.getDescription() : "" %></textarea>
    </div>

    <div class="form-group">
        <label for="ordre">Ordre :</label>
        <input type="number" id="ordre" name="ordre" value="<%= etape.getOrdre() %>" required>
    </div>

    <div class="form-group">
        <label for="statut">Statut :</label>
        <select id="statut" name="statut">
            <option value="AFAIRE" <%= (etape.getStatutEtape() != null && "AFAIRE".equals(etape.getStatutEtape().name())) ? "selected" : "" %>>À faire</option>
            <option value="ENCOURS" <%= (etape.getStatutEtape() != null && "ENCOURS".equals(etape.getStatutEtape().name())) ? "selected" : "" %>>En cours</option>
            <option value="TERMINE" <%= (etape.getStatutEtape() != null && "TERMINE".equals(etape.getStatutEtape().name())) ? "selected" : "" %>>Terminé</option>
        </select>
    </div>

    <div class="form-group">
        <label for="projetId">ID Projet :</label>
        <input type="number" id="projetId" name="projetId" value="<%= (etape.getProjet() != null) ? etape.getProjet().getId() : "" %>" required>
    </div>

    <button type="submit">Enregistrer les modifications</button>
    <a href="${pageContext.request.contextPath}/etape" class="btn-cancel">Annuler</a>
</form>
<% } else { %>
<p class="error">Impossible de charger les données de cette étape.</p>
<a href="${pageContext.request.contextPath}/etape" class="btn-cancel" style="margin-left: 0;">Retour à la liste</a>
<% } %>

</body>
</html>
