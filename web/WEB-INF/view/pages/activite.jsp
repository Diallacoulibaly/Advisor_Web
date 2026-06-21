<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Activite" %>
<%@ page import="main.java.model.classes.Depense" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activite</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/activite.css">
    <link rel="stylesheet" href="https://cloudflare.com" />

    <style>
        .finish-btn:disabled {
            background-color: #cccccc !important;
            cursor: not-allowed;
            opacity: 0.6;
        }
    </style>
</head>
<body>

<%
    List<Activite> activiteList = (List<Activite>) request.getAttribute("activiteList");
    Integer idEtape = (Integer) request.getAttribute("idEtape");
    String titreEtape = (String) request.getAttribute("titreEtape");
    String descEtape = (String) request.getAttribute("descEtape");
    Integer idProjet = (Integer) request.getAttribute("idProjet");
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
                    <div class="check-title">
                        <input type="checkbox" name="valider" class="checkbox-activite">
                        <span><%= activite.getTitre() %></span>
                    </div>
                    <input type="hidden" name="action" value="ajouter">
                    <input type="hidden" name="idActivite" value="<%= activite.getId() %>">
                    <input type="hidden" name="idEtape" value="<%= idEtape %>">
                    <input class="inp-depense" type="number" id="montant" name="montant"  placeholder=" Saisir le montant">
                    <textarea class="inp-desc" id="description" name="description" rows="4" cols="30"></textarea>
                    <button class="save" type="submit"> Enregistrer </button>
                                <a href="depenses?action=lister&idActivite=<%= activite.getId() %>">
                                    Voir les dépenses
                                </a>

                </div>
            </form>
        </li>
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

        <form action="${pageContext.request.contextPath}/validerEtape" method="post" style="display:inline;">
            <input type="hidden" name="idEtape" value="<%= idEtape %>">
            <input type="hidden" name="idProjet" value="<%= idProjet %>">
            <button type="submit" id="btn-terminer" class="finish-btn" disabled>
                <div class="btnpd">
                    Etape terminée <i class="fa-regular fa-circle-check fa-lg"></i>
                </div>
            </button>
        </form>
    </div>
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
