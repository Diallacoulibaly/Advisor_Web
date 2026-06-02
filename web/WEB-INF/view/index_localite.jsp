<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Localite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Localite> lst=(List<Localite>) request.getAttribute("lstLocalite");%>
<html>
<head>
    <title>La liste des Localités</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/localite.css">
</head>

<body>
<h1>La liste des Localités</h1>
<a href="localites?action=add" class="btn-ajout">Ajout d'une localité</a>
<table>
  <tr>
    <th>ID</th>
    <th>Nom</th>
    <th>Actions</th>
  </tr>
  <% for (Localite l:lst){ %>
  <tr>
    <td><%= l.getId() %></td>
    <td><%= l.getRegionClient() %></td>
    <td>
      <a class="edit" href="localites?action=edit&id=<%=l.getId()%>">Modifier</a>
      <a class="delete" href="localites?action=delete&id=<%=l.getId()%>">Supprimer</a>
    </td>
  </tr>
  <% } %>
</table>
</body>
</html>
