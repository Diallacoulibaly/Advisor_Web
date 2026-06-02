<%@ page import="main.java.model.classes.Activite" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="main.java.model.classes.Etape" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 02/06/2026
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Activite> activiteList = (List<Activite>) request.getAttribute("activites");
 Etape et=(Etape) request.getAttribute("etape");
        int idEtape=et.getIdEtape();%>
<html>

<head>
    <title>Title</title>
</head>
<body>

<h1><%= et.getTitre() %></h1>

<p><%= et.getDescription() %></p>

<hr>

<h2>Activités</h2>

<ul>
    <%
        for (Activite a : activiteList) {
    %>

    <li>
        <%= a.getDescription() %>

        <form action="depense" method="post">

            <!-- ID CORRECT -->
            <input type="hidden" name="idActivite" value="<%= a.getId() %>">

            <input type="text" name="montant" placeholder="Montant">
            <input type="text" name="description" placeholder="Description">

            <button type="submit">Ajouter dépense</button>

        </form>
    </li>

    <%
        }
    %>
</ul>


<h3>Commentaire</h3>

<a href="commentaires?actions=addCmt&idEtape=<%= idEtape %>">
    <button>Ajouter commentaire</button>
</a>

<hr>




</body>
</html>
