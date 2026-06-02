<%@ page import="main.java.model.classes.Localite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Localite idRegion=(Localite) request.getAttribute("localite"); %>
<html>
<head>
    <title>Modifier la localité</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/localite.css">
</head>

<body>
<div class="container">
    <a href="localites" class="btn-retour">← Retour à la liste</a>
    <h1>Modification de Localité</h1>
    <form action="localites" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=idRegion.getId()%>">
        <input type="text" name="regionClient" value="<%=idRegion.getRegionClient()%>" required>
        <button type="submit">Modifier</button>
    </form>
</div>
</body>
</html>
