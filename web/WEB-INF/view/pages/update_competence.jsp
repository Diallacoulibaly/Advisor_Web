<%@ page import="main.java.model.classes.Competence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Competence competence = (Competence) request.getAttribute("competence");
%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/competence.css">
</head>
<body>

<div class="form-wrapper">
    <div class="form-card">

        <h2>Modifier la compétence</h2>
        <p class="form-subtitle">Modifiez le nom puis enregistrez.</p>

        <form action="${pageContext.request.contextPath}/competences?action=modifier" method="post">

            <input type="hidden" name="id" value="<%= competence.getId() %>">

            <div class="form-group">
                <label for="nom">Nom de la compétence</label>
                <input type="text" id="nom" name="nom"
                       value="<%= competence.getNom() %>" required>
            </div>

            <button type="submit" class="btn-submit modifier">
                Enregistrer les modifications
            </button>

        </form>

        <a href="${pageContext.request.contextPath}/competences" class="btn-retour">
            ← Retour à la liste
        </a>

    </div>
</div>

</body>
</html>
