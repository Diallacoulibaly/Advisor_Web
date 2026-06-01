<%@ page import="main.java.model.classes.Projet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: seyys
  Date: 5/29/26
  Time: 11:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projets</title>
</head>
<body>
<%List<Projet> projets = (List<Projet>) request.getAttribute("projets");%>
<table style="border: black 1px solid">
    <tr>
        <th>--</th>
        <th>Titre</th>
        <th>Budget Min</th>
        <th>Budget Max</th>
        <th>Durée</th>
    </tr>
    <% for (int i=0 ; i<projets.size(); i++){
    %>
    <tr>
        <td> <%= i+1 %> </td>
        <td> <%= projets.get(i).getTitre() %> </td>
        <td> <%= projets.get(i).getBudgetMin() %> </td>

        <td> <%= projets.get(i).getBudgetMax() %> </td>
        <td> <%= projets.get(i).getDuree() %> Jours </td>
    </tr>

    <%
        }
    %>


</table>

</body>

</html>
