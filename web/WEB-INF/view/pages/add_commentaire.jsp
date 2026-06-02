<%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 01/06/2026
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int idEtape = Integer.parseInt(request.getParameter("idEtape"));
%>
<html>
<head>
    <title>Add Commmentaire</title>
</head>
<body>
<div class="container">

    <h1>Formulaire d'ajout de Commentaire</h1>
    <form action="commentaires?actions=addCmt" method="post">
        <input type="text" name="cmt" id="" placeholder="Entrez un Commentaire">
        <input type="hidden" name="idEtape" value="<%= idEtape %>">



        <button type="submit">Ajouter</button>

    </form>
</div>
</body>
</html>
