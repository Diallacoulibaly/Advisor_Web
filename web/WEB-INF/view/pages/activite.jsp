<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Activite" %><%--
  Created by IntelliJ IDEA.
  User: Cute Boy
  Date: 28/05/2026
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activite.css">

    <title>Activite</title>
</head>
<body>
    <style>
        .form-depense {
        display: flex;
        flex-direction: column;
        width: 300px;
        gap: 10px;
    }</style>
    <% List<Activite> activiteList = (List<Activite>) request.getAttribute("activiteList");
    Integer idEtape=(Integer) request.getAttribute("idEtape");
    %>

    <h1> Liste des activités </h1>
    <ol>
        <% for (Activite activite : activiteList){ %>
        <li>
            <%= activite.getTitre() %>
            <form action="activite" method="post">
                <input type="hidden" name="action" value="terminer">
                <input type="hidden" name="id" value="<%= activite.getId()%>">
                <button type="submit"> Terminer </button>
            </form>

<%--                <form action="activite" method="post">--%>
<%--                    <input type="hidden" name="action" value="supprimer">--%>
<%--                    <input type="hidden" name="id" value="<%= activite.getId()%>">--%>
<%--                    <button type="submit"> Supprimer </button>--%>
<%--                </form>--%>
            </li>

            <% } %>
        </ol>

        <button class="commentaire"><a href="commentaires?actions=addCmt&idEtape=<%=idEtape%>">Ajouter un commentaire</a></button>
            </div>
    </section>
</body>
</html>
