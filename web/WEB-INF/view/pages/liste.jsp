<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yourpackage.entity.Depense" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des dépenses</title>


</head>

<body>

<h2>Liste des dépenses</h2>


<a class="btn-back" href="activites"> Retour aux activités</a>

<hr>

<table>

    <tr>
        <th>ID</th>
        <th>Montant (FCFA)</th>
        <th>Description</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>

    <%
        List<Depense> depenses = (List<Depense>) request.getAttribute("depenses");

        if (depenses != null && !depenses.isEmpty()) {

            for (Depense d : depenses) {
    %>

    <tr>
        <td><%= d.getIdDepense() %></td>
        <td><%= d.getMontant() %> FCFA</td>
        <td><%= d.getDescription() %></td>
        <td><%= d.getDate() %></td>

        <td>

            <a href="depenses?action=modifier&idDepense=<%= d.getIdDepense() %>">
                Modifier
            </a>

            |


            <a href="depenses?action=supprimer&idDepense=<%= d.getIdDepense() %>&idActivite=<%= d.getActivite().getId() %>"
               onclick="return confirm('Voulez-vous vraiment supprimer cette dépense ?')">
                 Supprimer
            </a>

        </td>
    </tr>

    <%
            }
        } else {
    %>

    <tr>
        <td colspan="5">
             Aucune dépense trouvée pour cette activité
        </td>
    </tr>

    <% } %>

</table>

</body>
</html>