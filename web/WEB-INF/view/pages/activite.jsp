<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Activite" %>
<%@ page import="main.java.model.classes.Depense" %>
<%@ page import="main.java.model.classes.Etape" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activite</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activite.css">
    <link rel="stylesheet" href="https://cloudflare.com" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activite.css">



</head>
<body>

<%
    List<Activite> activiteList = (List<Activite>) request.getAttribute("activiteList");
    Integer idEtape = (Integer) request.getAttribute("idEtape");
    String titreEtape = (String) request.getAttribute("titreEtape");
    String descEtape = (String) request.getAttribute("descEtape");
    Integer idProjet = (Integer) request.getAttribute("idProjet");
    Etape etape = (Etape) request.getAttribute("etapeObject");
%>

<div class="etape-header">
    <h1>Étape <%= etape.getOrdre() %> : <%= etape.getTitre() %></h1>
    <p><%= etape.getDescription() %></p>
</div>

<div class="activities-container">

    <% for (Activite activite : activiteList) { %>

    <div class="activity-card">

        <div class="activity-top">

            <div class="activity-title">
                <h3><%= activite.getTitre() %></h3>
            </div>

            <form action="terminerActivite" method="post">

                <input type="hidden"
                       name="idActivite"
                       value="<%= activite.getId() %>">


            </form>

        </div>

        <form action="depenses" method="post">

            <input type="hidden" name="action" value="ajouter">
            <input type="hidden" name="idActivite" value="<%= activite.getId() %>">
            <input type="hidden" name="idEtape" value="<%= idEtape %>">

            <div class="form-grid">

                <div class="field">

                    <label>Montant dépensé</label>

                    <input
                            type="number"
                            name="montant"
                            placeholder="Ex: 50000">

                </div>

                <div class="field">

                    <label>Description de la dépense</label>

                    <textarea
                            name="description"
                            rows="4"
                            placeholder="Décrivez la dépense..."></textarea>

                </div>

            </div>

            <button class="btn-save" type="submit">
                Enregistrer la dépense
            </button>

        </form>

    </div>

    <% } %>

</div>

<div class="bottom-actions">

    <a
            class="btn-comment"
            href="commentaires?actions=addCmt&idEtape=<%= idEtape %>">

        Commentaires de l'étape

    </a>

    <form action="${pageContext.request.contextPath}/validerEtape"
          method="post">

        <input type="hidden"
               name="idEtape"
               value="<%= idEtape %>">

        <input type="hidden"
               name="idProjet"
               value="<%= idProjet %>">

        <button type="submit" class="btn-etape">
            Terminer l'étape
        </button>

    </form>

</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const checkboxes = document.querySelectorAll('.checkbox-activite');
        const btnTerminer = document.getElementById('btn-terminer');

        function verifierCheckboxes() {
            if (checkboxes.length === 0) {
                btnTerminer.disabled = true;
                return;
            }

            const toutesCochees = Array.from(checkboxes).every(cb => cb.checked);
            btnTerminer.disabled = !toutesCochees;
        }

        checkboxes.forEach(cb => {
            cb.addEventListener('change', verifierCheckboxes);
        });
        verifierCheckboxes();
    });
</script>

</body>
</html>
