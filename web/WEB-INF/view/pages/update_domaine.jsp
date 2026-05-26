<%@ page import="main.java.model.classes.Domaine" %><%--
  Created by IntelliJ IDEA.
  User: Dialla COULOUBALY
  Date: 25/05/2026
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%Domaine d=(Domaine )request.getAttribute("domaine"); %>
<head>
    <title>Title</title>
</head>
<body>
<h1>Formulaire de Modification</h1>
<form action="domaines?actions=updateDomaine" method="post">
    <input type="hidden" name="id" id="" value="<%=d.getId()%>" >
    <input type="text" name="domaine" id="" value="<%=d.getDomaine()%>" >
    <input type="submit" value="Modifier">

</form>
</body>
</html>
