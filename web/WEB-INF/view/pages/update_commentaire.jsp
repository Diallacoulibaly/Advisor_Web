<%@ page import="main.java.model.classes.Commentaire" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 01/06/2026
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%Commentaire c=(Commentaire ) request.getAttribute("cmt"); %>
<head>
    <title>Title</title>
</head>
<body>
<h1>Formulaire de Modification</h1>
<form action="commentaires?actions=updateCmt" method="post">
    <input type="hidden" name="id" id="" value="<%=c.getId()%>" >
    <input type="text" name="cmt" id="" value="<%=c.getMessage()%>" >
    <input type="submit" value="Modifier">

</form>
<a href="commentaires" class="btn-retour">Retour à la liste</a>
</body>
</html>
