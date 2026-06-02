<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Commentaire" %><%--

  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 01/06/2026
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List <Commentaire> c=(List<Commentaire>) request.getAttribute("cmt");%>
<html>
<head>
    <title>La liste des commentaires</title>
</head>
<body>
<a href="commentaires?actions=addCmt" class="btn-ajout">
    <button>Ajouter un Domaine</button>
</a>
<h1>La liste des Commentaires</h1>
<table border="1">
    <tr>

        <th>ID</th>
        <th>MESSAGE</th>
        <th>Actions</th>

    </tr>
    <% for (int i=0 ; i<c.size();i++){
    %>
    <tr>

        <td>
            <%= i+1 %>
        </td>

        <td>
            <%= c.get(i).getMessage() %>
        </td>

        <td>
            <a class="edit" href="commentaires?actions=editCmt&id=<%= c.get(i).getId() %>">

                Modifier

            </a>


            <a class="delete" href="commentaires?actions=deleteCmt&id=<%= c.get(i).getId() %>">

                Supprimer

            </a>

        </td>

    </tr>

    <%
        }
    %>


</table>

</body>
</html>
