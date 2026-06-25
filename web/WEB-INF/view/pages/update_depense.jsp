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

    Integer idProjet = (Integer) request.getAttribute("idProjet");
    Integer idEtape = (Integer) request.getAttribute("idEtape");
    Integer idActivite = (Integer) request.getAttribute("idActivite");
%>

<% if (depense != null) { %>

<form action="depenses?action=update" method="post">

    <input type="hidden" name="idDepense" value="<%= depense.getIdDepense() %>">
    <input type="hidden" name="idActivite" value="<%= idActivite %>">
    <input type="hidden" name="idEtape" value="<%= idEtape %>">
    <input type="hidden" name="idProjet" value="<%= idProjet %>">

    <label>Montant</label><br>
    <input type="number" name="montant" value="<%= depense.getMontant() %>" required>

    <br><br>

    <label>Description</label><br>
    <textarea name="description"><%= depense.getDescription() %></textarea>

    <br><br>

    <button type="submit">Modifier</button>
</form>

<% } else { %>

<p style="color:red;">Aucune dépense trouvée</p>

<% } %>


</body>
</html>