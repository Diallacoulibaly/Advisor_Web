<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int idEtape = Integer.parseInt(request.getParameter("idEtape"));


%>

<html>
<head>
    <title>Ajouter un commentaire</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/commentaire.css">
</head>

<body>

<div class="container">

    <h1>Ajouter un commentaire</h1>

    <form action="commentaires?actions=addCmt" method="post">

        <input
                type="text"
                name="cmt"
                placeholder="Entrez votre commentaire">

        <input
                type="hidden"
                name="idEtape"
                value="<%= idEtape %>">

        <button type="submit">
            Ajouter
        </button>

    </form>

</div>

</body>
</html>