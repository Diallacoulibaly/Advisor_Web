<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Etape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Récupération de la liste (gérée au pluriel par la première servlet)
    List<Etape> etapes = (List<Etape>) request.getAttribute("etapes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Étapes</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #f4f4f4; }
        .btn { padding: 8px 12px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; }
        .btn-edit { color: #007bff; text-decoration: none; font-weight: bold; }
        .btn-delete { color: #dc3545; text-decoration: none; font-weight: bold; margin-left: 10px; }
    </style>
</head>
<body>

<h1>La liste des Étapes</h1>

<div style="margin-bottom: 20px;">
    <a href="ajout_etape" class="btn">Ajout d'une étape</a>
</div>

<table>
    <thead>
    <tr>
        <th>Ordre</th>
        <th>Titre</th>
        <th>Description</th>
        <th>Statut</th>
        <th>ID Projet</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (etapes != null && !etapes.isEmpty()) {
            for (Etape item : etapes) {
    %>
    <tr>
        <td><%= item.getOrdre() %></td>
        <td><%= item.getTitre() %></td>
        <td><%= item.getDescription() %></td>
        <td><%= item.getStatutEtape() != null ? item.getStatutEtape().name() : "NON SPÉCIFIÉ" %></td>
        <td><%= item.getProjet() != null ? item.getProjet().getId() : "Aucun" %></td>
        <td>
            <!-- Paramètres d'action attendus par la première servlet -->
            <a class="btn-edit" href="etape?action=modifier&id=<%= item.getIdEtape() %>">Modifier</a>
            <a class="btn-delete" href="etape?action=supprimer&id=<%= item.getIdEtape() %>" onclick="return confirm('Supprimer cette étape ?');">Supprimer</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="6" style="text-align: center; color: #666; padding: 20px;">Aucune étape disponible dans la base de données.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
