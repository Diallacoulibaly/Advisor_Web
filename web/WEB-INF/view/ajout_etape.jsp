<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter une Étape</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .form-group { margin-bottom: 15px; width: 300px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, textarea, select { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 15px; background-color: #28a745; color: white; border: none; cursor: pointer; }
        .error { color: red; margin-bottom: 15px; }
    </style>
</head>
<body>

<h2>Ajouter une nouvelle étape</h2>

<!-- Affichage de l'erreur en cas d'échec de la servlet AjoutEtape -->
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<p class="error"><%= error %></p>
<% } %>

<form action="${pageContext.request.contextPath}/ajout_etape" method="post">
    <div class="form-group">
        <label for="titre">Titre :</label>
        <input type="text" id="titre" name="titre" required>
    </div>

    <div class="form-group">
        <label for="description">Description :</label>
        <textarea id="description" name="description" rows="3" required></textarea>
    </div>

    <div class="form-group">
        <label for="ordre">Ordre :</label>
        <input type="number" id="ordre" name="ordre" required>
    </div>

    <div class="form-group">
        <label for="statut">Statut :</label>
        <select id="statut" name="statut">
            <option value="A_FAIRE">À faire</option>
            <option value="ENCOURS">En cours</option>
            <option value="TERMINE">Terminé</option>
        </select>
    </div>

    <div class="form-group">
        <label for="projetId">ID Projet :</label>
        <input type="number" id="projetId" name="projetId" required>
    </div>

    <button type="submit">Enregistrer l'étape</button>
    <a href="${pageContext.request.contextPath}/etape" style="margin-left:10px;">Annuler</a>
</form>

</body>
</html>
