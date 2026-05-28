<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Etape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Etape> etapes = (List<Etape>) request.getAttribute("etape");
%>
<html>
<head>
    <title>Liste des Étapes</title>
</head>
<body>

<h1>La liste des Étapes</h1>

<!-- Redirection vers le doGet de votre Servlet AjoutEtape -->
<a href="ajout_etape">
    <button>Ajout d'une étape</button>
</a>

<table border="1" style="margin-top: 20px; width: 100%;">
    <tr>
        <th>Ordre</th>
        <th>Titre</th>
        <th>Description</th>
        <th>Statut</th>
        <th>Actions</th>
    </tr>
    <%
        if (etapes != null) {
            for (Etape item : etapes) {
    %>
    <tr>
        <td><%= item.getOrdre() %></td>
        <td><%= item.getTitre() %></td>
        <td><%= item.getDescription() %></td>
        <td><%= item.getStatutEtape() %></td>
        <td>

            <a href="etape?action=miseAjour&id=<%= item.getIdEtape() %>">Modifier</a>
            |
            <a href="etape?action=suppression&id=<%= item.getIdEtape() %>" onclick="return confirm('Supprimer cette étape ?');">Supprimer</a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>
