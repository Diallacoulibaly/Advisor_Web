<%@ page import="main.java.model.classes.Etape" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 02/06/2026
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List <Etape> et=(List<Etape>) request.getAttribute("et");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>La liste des etapes qui constitut un projet donné</h1>
<table border="1">
    <tr>

        <th>TITRE</th>
        <th>DESC</th>

    </tr>
    <% for (int i = 0; i < et.size(); i++) { %>

    <tr>

        <td>
            <a href="etps?action=addEt&idEtape=<%= et.get(i).getIdEtape() %>">
                <%= et.get(i).getTitre() %>
            </a>
        </td>

        <td>
            <%= et.get(i).getDescription() %>
        </td>

    </tr>

    <% } %>


</table>

</body>
</html>
