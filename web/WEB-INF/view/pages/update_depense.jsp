<%@ page import="main.java.model.classes.Depense" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Modifier dépense</title>
</head>

<body>

<h2>Modifier la dépense</h2>

<%
    Depense depense = (Depense) request.getAttribute("depense");
%>

<% if (depense != null) { %>

<form action="depenses?action=update" method="post">

    <input type="hidden" name="action" value="update">

    <input type="hidden" name="idDepense" value="<%= depense.getIdDepense() %>">

    <input type="hidden" name="idActivite" value="<%= depense.getActivite().getId() %>">

    <label>Montant :</label><br>
    <input type="number" name="montant" value="<%= depense.getMontant() %>" required>
    <br><br>

    <label>Description :</label><br>
    <textarea name="description" required><%= depense.getDescription() %></textarea>
    <br><br>

    <button type="submit">Modifier</button>

</form>

<% } else { %>

<p>Aucune dépense trouvée.</p>

<% } %>

<br>

<a href="depenses?action=liste&idActivite=<%= depense.getActivite().getId() %>">
    Retour à la liste
</a>

</body>
</html>