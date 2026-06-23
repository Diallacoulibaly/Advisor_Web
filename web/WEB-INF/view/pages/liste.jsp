<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Depense" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des dépenses</title>
</head>

<body>

<h2>Liste des dépenses</h2>

<%
    List<Depense> depenses = (List<Depense>) request.getAttribute("depenses");
    Integer idProjet = (Integer) request.getAttribute("idProjet");
    Integer idEtape = (Integer) request.getAttribute("idEtape");
%>

<a href="etape_activite?idEtape=<%=idEtape%>&idProjet=<%=idProjet%>">
    Retour aux activités
</a>

<br><br>

<table border="1" cellpadding="8" cellspacing="0">

    <tr>
        <th>ID</th>
        <th>Montant (FCFA)</th>
        <th>Description</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>

    <%
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

            <a href="depenses?action=supprimer&idDepense=<%= d.getIdDepense() %>&idActivite=<%= d.getActivite().getId() %>">
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