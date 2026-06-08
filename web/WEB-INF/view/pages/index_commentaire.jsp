<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Commentaire" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Commentaire> c = (List<Commentaire>) request.getAttribute("cmt");
%>

<html>
<head>
    <title>Liste des commentaires</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/commentaire.css">
</head>

<body>

<div class="container">

    <!-- <div class="btn-container">
        <a  href="${pageContext.request.contextPath}/etape_activite" class="btn-ajout">
            Retour
        </a>
    </div>-->

    <h1>Liste des commentaires</h1>

    <table>

        <tr>
            <th>ID</th>
            <th>Message</th>
            <th>Actions</th>
        </tr>

        <% for(int i = 0; i < c.size(); i++){ %>

        <tr>

            <td><%= i + 1 %></td>

            <td><%= c.get(i).getMessage() %></td>

            <td>
                <a class="edit"
                   href="commentaires?actions=editCmt&id=<%= c.get(i).getId() %>">
                    Modifier
                </a>

                <a class="delete"
                   href="commentaires?actions=deleteCmt&id=<%= c.get(i).getId() %>">
                    Supprimer
                </a>
            </td>

        </tr>

        <% } %>

    </table>

</div>

</body>
</html>