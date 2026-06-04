<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.*" %>
<%@ page import="main.java.model.enums.Niveau" %>

<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Faire une recommandation</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/formRecomm.css">


</head>

<body>
    <%
    List<Localite> localites =
            (List<Localite>) request.getAttribute("localites");

    List<Domaine> domaines =
            (List<Domaine>) request.getAttribute("domaines");

    List<Competence> competences =
            (List<Competence>) request.getAttribute("competences");
%>

    <div class="recommendation-container">

        <div class="recommendation-card">

            <div class="form-header">

                <h1 class="form-title">
                    Demande de recommandation
                </h1>

                <p class="form-subtitle">
                    Obtenez des projets adaptés à votre profil
                </p>

            </div>

            <form action="${pageContext.request.contextPath}/addRecommandation"
                method="post">

            

                <div class="form-group">

                    <label for="idLocalite">
                        Localité
                    </label>

                    <select name="idLocalite"
                            id="idLocalite"
                            required>

                        <option value="">
                            -- Choisir une localité --
                        </option>

                        <% if(localites != null){ %>

                        <% for(Localite localite : localites){ %>

                        <option value="<%= localite.getId() %>">
                            <%= localite.getRegionClient() %>
                        </option>

                        <% } %>

                        <% } %>

                    </select>

                </div>

                

                <div class="form-group">

                    <label for="idDomaine">
                        Domaine
                    </label>

                    <select name="idDomaine"
                            id="idDomaine"
                            required>

                        <option value="">
                            -- Choisir un domaine --
                        </option>

                        <% if(domaines != null){ %>

                        <% for(Domaine domaine : domaines){ %>

                        <option value="<%= domaine.getId() %>">
                            <%= domaine.getDomaine() %>
                        </option>

                        <% } %>

                        <% } %>

                    </select>

                </div>

                

                <div class="form-group">

                    <label for="niveau">
                        Niveau
                    </label>

                    <select name="niveau"
                            id="niveau"
                            required>

                        <option value="">
                            -- Choisir votre niveau --
                        </option>

                        <option value="<%= Niveau.DEBUTANT.name() %>">
                            Débutant
                        </option>

                        <option value="<%= Niveau.INTERMEDIAIRE.name() %>">
                            Intermédiaire
                        </option>

                        <option value="<%= Niveau.EXPERT.name() %>">
                            Expert
                        </option>

                    </select>

                </div>

                

                <div class="form-group">

                    <label for="budget">
                        Budget disponible
                    </label>

                    <input type="number"
                        id="budget"
                        name="budget"
                        min="0"
                        required>

                </div>

            

                <div class="form-group competence-group">

                    <label>
                        Compétences
                    </label>

                    <div class="checkbox-container">

                        <% if(competences != null){ %>

                        <% for(Competence competence : competences){ %>

                        <div class="checkbox-item">

                            <input
                                    type="checkbox"
                                    id="comp_<%= competence.getId() %>"
                                    name="competences"
                                    value="<%= competence.getId() %>">

                            <label
                                    for="comp_<%= competence.getId() %>">

                                <%= competence.getNom() %>

                            </label>

                        </div>

                        <% } %>

                        <% } %>

                    </div>

                </div>

                <button type="submit"
                        class="btn-submit">

                    Obtenir mes recommandations

                </button>
                <% if (request.getAttribute("error")!= null) {%>
                <h3 style="color: red; font-weight: bold">${error}</h3>
                <%
                    }
                %>

            </form>

        </div>

    </div>
</body>

</html>