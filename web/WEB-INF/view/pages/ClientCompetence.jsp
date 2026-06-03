<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Client" %>
<%@ page import="main.java.model.classes.Competence" %>

<%
    List<Client> clients = (List<Client>) request.getAttribute("clients");
    List<Competence> competences = (List<Competence>) request.getAttribute("competences");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Association Client - Compétence</title>
</head>
<body>

<h2>Associer un client à une compétence</h2>

<form action="clientCompetences" method="post">

    <label>Client :</label>
    <select name="clientId" required>
        <option value="">-- Sélectionner un client --</option>
        <% for(Client c : clients) { %>
        <option value="<%= c.getIdUtilisateur() %>">
            <%= c.getNom() %>
        </option>
        <% } %>
    </select>

    <br><br>

    <label>Compétence :</label>
    <select name="competenceId" required>
        <option value="">-- Sélectionner une compétence --</option>
        <% for(Competence comp : competences) { %>
        <option value="<%= comp.getId() %>">
            <%= comp.getNom() %>
        </option>
        <% } %>
    </select>

    <br><br>

    <button type="submit">Enregistrer</button>

</form>

</body>
</html>