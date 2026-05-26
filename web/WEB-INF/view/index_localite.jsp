<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Localite" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 26/05/2026
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Localite> lst=(List<Localite>) request.getAttribute("lstLocalite");%>
        <html>

<head>
    <title>Title</title>
</head>

<body>
<h1>La liste des Localités</h1>
<a href="localites?action=add">
  <button>Ajout d'une localité</button>
</a>
<table border="1">
  <tr>

    <th>ID</th>
    <th>Nom</th>
    <th>Actions</th>

  </tr>
  <% for (Localite l:lst){
  %>
  <tr>

    <td>
      <%= l.getId() %>
    </td>

    <td>
      <%= l.getRegionClient() %>
    </td>

    <td>
      <a href="">

        Modifier

      </a>


      <a href="">

        Supprimer

      </a>

    </td>

  </tr>

  <%
    }
  %>


</table>
