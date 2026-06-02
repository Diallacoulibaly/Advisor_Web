<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter une localité</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/localite.css">
</head>
<body>
<div class="container">
    <a href="localites" class="btn-retour">← Retour à la liste</a>
    <h1>Ajout de localité</h1>
    <form action="localites" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="regionClient" placeholder="Nom de la région/localité" required>
        <button type="submit">Ajouter</button>
    </form>
</div>
</body>
</html>
