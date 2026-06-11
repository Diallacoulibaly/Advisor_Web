<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Competence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Competence> competences = (List<Competence>) request.getAttribute("competences");
%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/competence.css">
</head>
<body>

<div class="page-header">
    <h1 class="page-title">Gestion des compétences</h1>
    <a href="${pageContext.request.contextPath}/competences?action=ajouter" class="btn-primary">
        + Ajouter une compétence
    </a>
</div>

<div class="table-card">
    <table class="data-table">
        <thead>
            <tr>
                <th>#</th>
                <th>Nom de la compétence</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% if (competences == null || competences.isEmpty()) { %>
                <tr>
                    <td colspan="3" class="empty-state">Aucune compétence enregistrée pour le moment.</td>
                </tr>
            <% } else {
                for (int i = 0; i < competences.size(); i++) {
            %>
                <tr>
                    <td><%= i + 1 %></td>
                    <td>
                        <span class="badge"><%= competences.get(i).getNom() %></span>
                    </td>
                    <td class="actions">
                        <a href="${pageContext.request.contextPath}/competences?action=editer&id=<%= competences.get(i).getId() %>"
                           class="btn-edit">Modifier</a>
                        <a href="${pageContext.request.contextPath}/competences?action=supprimer&id=<%= competences.get(i).getId() %>"
                           class="btn-delete"
                           onclick="return confirm('Confirmer la suppression de cette compétence ?')">Supprimer</a>
                    </td>
                </tr>
            <% } } %>
        </tbody>
    </table>
</div>

</body>
</html>
