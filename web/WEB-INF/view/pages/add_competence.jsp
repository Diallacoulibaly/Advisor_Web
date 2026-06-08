<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/competence.css">
</head>
<body>

<div class="form-wrapper">
    <div class="form-card">

        <h2>Nouvelle compétence</h2>
        <p class="form-subtitle">Remplissez le champ ci-dessous pour ajouter une compétence.</p>

        <form action="${pageContext.request.contextPath}/competences?action=ajouter" method="post">

            <div class="form-group">
                <label for="nom">Nom de la compétence</label>
                <input type="text" id="nom" name="nom"
                       placeholder="Ex : Java, Photoshop, Marketing..." required>
            </div>

            <button type="submit" class="btn-submit">Ajouter la compétence</button>

        </form>

        <a href="${pageContext.request.contextPath}/competences" class="btn-retour">
            ← Retour à la liste
        </a>

    </div>
</div>

</body>
</html>
