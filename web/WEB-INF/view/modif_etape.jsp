<%@ page import="main.java.model.classes.Etape" %>
<%@ page import="main.java.model.enums.StatutEtape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Récupération de l'étape à modifier transmise par le doGet
    Etape etape = (Etape) request.getAttribute("etape");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier une Étape</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/stylee.css">
</head>
<body>

<h1>Modifier l'Étape</h1>

<%-- Affichage des messages d'erreur--%>
<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div class="error-msg"><%= error %></div>
<% } %>

<% if (etape != null) { %>
<form action="etape?action=Modif" method="post" style="max-width: 500px;">

    <!-- Champ caché pour transmettre l'ID à mettre à jour -->
    <input type="hidden" name="idEtape" value="<%= etape.getIdEtape() %>">

    <div class="form-group">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" value="<%= etape.getTitre() %>" required>
    </div>

    <div class="form-group">
        <label for="description">Description :</label>
        <textarea id="description" name="description" rows="4" required><%= etape.getDescription() %></textarea>
    </div>

    <div class="form-group">
        <label for="ordre">Ordre :</label>
        <input type="number" id="ordre" name="ordre" min="1" value="<%= etape.getOrdre() %>" required>
    </div>

    <div class="form-group">
        <label for="statut">Statut :</label>
        <select id="statut" name="statut" required>
            <%
                for (StatutEtape statut : StatutEtape.values()) {
                    String selected = (etape.getStatutEtape() != null && statut == etape.getStatutEtape()) ? "selected" : "";
            %>
            <option value="<%= statut.name() %>" <%= selected %>><%= statut.name() %></option>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <label for="projetId">ID du Projet :</label>
        <input type="number" id="projetId" name="projetId"
               value="<%= etape.getProjet() != null ? etape.getProjet().getId() : "" %>" required>
    </div>

    <div style="margin-top: 20px;">
        <button type="submit" class="btn">Enregistrer les modifications</button>
        <a href="etape" class="btn" style="background-color: #6c757d;">Annuler</a>
    </div>
</form>
<% } else { %>
<div class="error-msg">Aucune donnée trouvée pour cette étape.</div>
<a href="etape" class="btn">Retour à la liste</a>
<% } %>

</body>
</html>
