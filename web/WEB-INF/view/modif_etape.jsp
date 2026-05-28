
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.java.model.classes.Etape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier une Étape</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .form-group { margin-bottom: 15px; width: 300px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, textarea, select { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 15px; background-color: #007bff; color: white; border: none; cursor: pointer; }
        .error { color: red; margin-bottom: 15px; }
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
<form action="${pageContext.request.contextPath}/etape?action=enregistrerModif" method="post">

    <!-- Champ masqué pour transmettre l'ID à la Servlet -->
    <input type="hidden" name="idEtape" value="<%= etape.getIdEtape() %>">

    <div class="form-group">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" value="<%= etape.getTitre() %>" required>
    </div>

    <div class="form-group">
        <label for="description">Description :</label>
        <textarea id="description" name="description" rows="3" required><%= etape.getDescription() %></textarea>
    </div>

    <div class="form-group">
        <label for="ordre">Ordre :</label>
        <input type="number" id="ordre" name="ordre" value="<%= etape.getOrdre() %>" required>
    </div>

    <div class="form-group">
        <label for="statut">Statut :</label>
        <select id="statut" name="statut">
            <option value="ENCOURS" <%= "ENCOURS".equals(etape.getStatutEtape()) ? "selected" : "" %>>En cours</option>
            <option value="TERMINE" <%= "TERMINE".equals(etape.getStatutEtape()) ? "selected" : "" %>>Terminé</option>
        </select>
    </div>

    <div class="form-group">
        <label for="projetId">ID Projet :</label>
        <input type="number" id="projetId" name="projetId" value="<%= etape.getProjetId() %>" required>
    </div>

    <button type="submit">Enregistrer les modifications</button>
    <a href="${pageContext.request.contextPath}/etape" style="margin-left:10px;">Annuler</a>
</form>
<% } else { %>
<p class="error">Étape introuvable ou non spécifiée.</p>
<a href="${pageContext.request.contextPath}/etape">Retour à la liste</a>
<% } %>

</body>
</html>

