<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Competence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Competence> competences = (List<Competence>) request.getAttribute("competences");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des compétences</title>
    <style>
        body { font-family: 'Poppins', sans-serif; background: #ebebed; padding: 30px; }
        h1 { color: #1e3a8a; margin-bottom: 20px; }
        .btn {
            display: inline-block;
            padding: 9px 18px;
            border-radius: 8px;
            text-decoration: none;
            font-size: 14px;
            cursor: pointer;
            border: none;
        }
        .btn-primary { background: #1e3a8a; color: #fff; margin-bottom: 20px; }
        .btn-edit { background: #f59e0b; color: #fff; }
        .btn-delete { background: #ef4444; color: #fff; margin-left: 6px; }
        table { width: 100%; border-collapse: collapse; background: #fff; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 20px rgba(0,0,0,0.08); }
        thead { background: #1e3a8a; color: #fff; }
        th, td { padding: 14px 18px; text-align: left; font-size: 14px; }
        tbody tr:nth-child(even) { background: #f0f4ff; }
        tbody tr:hover { background: #dce6ff; }
        .empty { text-align: center; color: #999; padding: 30px; }
    </style>
</head>
<body>

<h1>Liste des compétences</h1>

<a href="competences?action=ajouter" class="btn btn-primary">+ Ajouter une compétence</a>

<table>
    <thead>
        <tr>
            <th>#</th>
            <th>Nom de la compétence</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <% if (competences == null || competences.isEmpty()) { %>
            <tr><td colspan="3" class="empty">Aucune compétence enregistrée.</td></tr>
        <% } else { %>
            <% for (int i = 0; i < competences.size(); i++) { %>
                <tr>
                    <td><%= i + 1 %></td>
                    <td><%= competences.get(i).getNom() %></td>
                    <td>
                        <a href="competences?action=editer&id=<%= competences.get(i).getId() %>" class="btn btn-edit">Modifier</a>
                        <a href="competences?action=supprimer&id=<%= competences.get(i).getId() %>"
                           class="btn btn-delete"
                           onclick="return confirm('Supprimer cette compétence ?')">Supprimer</a>
                    </td>
                </tr>
            <% } %>
        <% } %>
    </tbody>
</table>

</body>
</html>
