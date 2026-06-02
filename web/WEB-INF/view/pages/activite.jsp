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
    <title>Activite</title>
</head>
<body>
    <% List<Activite> activiteList = (List<Activite>) request.getAttribute("activiteList");
    Integer idEtape=(Integer) request.getAttribute("idEtape");
    String titreEtape = (String) request.getAttribute("titreEtape");
    %>

    <div class="etape">
        <h1> Etape <%= idEtape %> : <%= titreEtape %></h1>
    </div>

    <ol>
        <% for (Activite activite : activiteList){ %>
        <li>
            <%= activite.getTitre() %>
            <form action="activite" method="post">
                <input type="hidden" name="action" value="terminer">
                <input type="hidden" name="id" value="<%= activite.getId()%>">
                <button type="submit"> Terminer </button>
            </form>

            <form action="activite" method="post">
                <input type="hidden" name="action" value="supprimer">
                <input type="hidden" name="id" value="<%= activite.getId()%>">
                <button type="submit"> Supprimer </button>
            </form>
        </>

        <% } %>
    </ol>

    <button><a href="commentaires?actions=addCmt&idEtape=<%=idEtape%>">Ajouter un commentaire</a></button>
</body>
</html>
