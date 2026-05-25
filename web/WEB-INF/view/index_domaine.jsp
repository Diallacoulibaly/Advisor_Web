<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Domaine" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 22/05/2026
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List <Domaine> domaines=(List<Domaine>) request.getAttribute("domaines");%>
<html>
<head>
    <title>La liste des domaines</title>
</head>
<body>


<a href="add-domaine">
    <button>Ajouter un Domaine</button>
</a>
<h1>La liste des domaines</h1>
<table border="1">
    <tr>

        <th>ID</th>
        <th>Nom</th>
        <th>Actions</th>

    </tr>
<% for (Domaine d:domaines){
%>
    <tr>

        <td>
            <%= d.getId() %>
        </td>

        <td>
            <%= d.getDomaine() %>
        </td>

        <td>
            <a href="update-domaine?id=<%= d.getId() %>">

                Modifier

            </a>


            <a href="delete-domaine?id=<%= d.getId() %>">

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
