<%@ page import="main.java.model.classes.Commentaire" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Commentaire c = (Commentaire) request.getAttribute("cmt");
%>
<head>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/commentaire.css">
</head>
<div class="container">

    <h1>Modifier un commentaire</h1>

    <form action="commentaires?actions=updateCmt" method="post">

        <input
                type="hidden"
                name="id"
                value="<%= c.getId() %>">

        <input
                type="text"
                name="cmt"
                value="<%= c.getMessage() %>">

        <button type="submit">
            Modifier
        </button>

    </form>

    <a href="commentaires" class="btn-retour">
        Retour à la liste
    </a>

</div>