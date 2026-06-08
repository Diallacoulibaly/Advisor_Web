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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activite.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

    <% List<Activite> activiteList = (List<Activite>) request.getAttribute("activiteList");
    Integer idEtape=(Integer) request.getAttribute("idEtape");
        String titreEtape = (String) request.getAttribute("titreEtape");
        String descEtape = (String) request.getAttribute("descEtape");
    %>
    <div class="etape">
        <h1> Etape <%= idEtape %>:<%=titreEtape%></h1>
        <p class="description"> <%=descEtape%></p>
    </div>
    <div class="bodyy">
    <div class="titre">
        <h2> Activités </h2>
        <div class="sey">
        <h2> Dépenses (F CFA) </h2>
        <h2> Description </h2>
        </div>
    </div>
    <ol>
        <% for (Activite activite : activiteList) { %>
        <li>
            <form action="depenses" method="post" class="form-depense">
                <div class="check">

                    <div class="check-title" >
                    <input type="checkbox" name="valider">
                    <span><%= activite.getTitre() %></span>
                    </div>
                <input type="hidden" name="action" value="ajouter">
                <input type="hidden" name="idActivite" value="<%= activite.getId() %>">

                <input type="hidden" name="idEtape" value="<%= idEtape %>">

                <input class="inp-depense" type="number" id="montant" name="montant" placeholder=" Saisir le montant">

                <textarea class="inp-desc" id="description" name="description" rows="4" cols="30" placeholder=""></textarea>
                    <button class="save" type="submit"> Enregistrer </button>
                </div>

            </form>
<%--            <form action="activite" method="post">--%>
<%--                <input type="hidden" name="action" value="terminer">--%>
<%--                <input type="hidden" name="id" value="<%= activite.getId()%>">--%>
<%--                <button type="submit"> Terminer </button>--%>
<%--            </form>--%>

<%--            <form action="activite" method="post">--%>
<%--                <input type="hidden" name="action" value="supprimer">--%>
<%--                <input type="hidden" name="id" value="<%= activite.getId()%>">--%>
<%--                <button type="submit"> Supprimer </button>--%>
<%--            </form>--%>
        </>

        <% } %>
    </ol>

    <div class="btn">
        <button class="coment-btn">
            <div class="btnpd">
            <a href="commentaires?actions=addCmt&idEtape=<%=idEtape%>">
                <i class="fa-solid fa-comments fa-lg"></i>Commentaire (étape)
            </a>
            </div>
        </button>
        <button class="finish-btn"> <div class="btnpd">Etape terminée <i class="fa-regular fa-circle-check fa-lg"></i>
        </div></button>
    </div>
    </div>
</body>
</html>
 
