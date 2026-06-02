<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Activite" %>
<%@ page import="main.java.model.classes.Depense" %>
<%--
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
    <style>
        .form-depense {
        display: flex;
        flex-direction: column;
        width: 300px;
        gap: 10px;
    }</style>
    <% List<Activite> activiteList = (List<Activite>) request.getAttribute("activiteList");
    Integer idEtape=(Integer) request.getAttribute("idEtape");
    List<Depense> depenseList = (List<Depense>) request.getAttribute("depense");
    %>

    <h1> Liste des activités </h1>
    <ol>
        <% for (Activite activite : activiteList){ %>
        <li>
            <%= activite.getTitre() %>
            <form action="depense" method="post" class="form-depense">
                <label for="montant">Montant :</label>
                <input type="number" id="montant" name="montant" step="0.01" placeholder="">
                <label for="montant">Description :</label>
                <textarea id="description" name="description" rows="4" cols="30" placeholder=""></textarea>
                <button type="submit">Enregistrer</button>
            </form>
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
