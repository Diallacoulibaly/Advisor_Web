<%@ page import="main.java.model.classes.Localite" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 26/05/2026
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Localite idRegion=(Localite) request.getAttribute("localite"); %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<h1>Formulaire de Modification de Localité</h1>
<form action="localites?action=update" method="post">
    <input type="hidden" name="id" value="<%=idRegion.getId()%>">
    <input type="text" name="regionClient" id="" value="<%=idRegion.getRegionClient()%>">
    <input type="submit" value="Modifier">
</form>
</body>
</html>
