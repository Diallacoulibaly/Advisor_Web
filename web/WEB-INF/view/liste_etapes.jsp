<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Etape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Etape> etapes = (List<Etape>) request.getAttribute("etapes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Étapes</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/stylee.css">
</head>
<body>

<h1>La liste des Étapes</h1>

<div style="margin-bottom: 20px;">
    <a href="etape?action=ajouter" class="btn">Ajouter une nouvelle etape</a>
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
            <a class="btn-edit" href="etape?action=modifier&id=<%= item.getIdEtape() %>">Modifier</a>
            <a class="btn-delete" href="etape?action=supprimer&id=<%= item.getIdEtape() %>" onclick="confirmerSuppression(event)">Supprimer</a>
            <a class="btn-activite" href="activite?idEtape=<%=item.getIdEtape()%>&titreEtape=<%= item.getTitre() %>"> Voir activités </a>
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


<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</body>
</html>
