<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25/05/2026
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sidebar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/sidebar.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/assets/js/sidebar.js"></script>
</head>
<body>
    <aside class="sidebar">

        <div class="logo-section">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo" class="logo">

            <h3>De l’idée à la réussite</h3>
        </div>


        <nav class="menu">

            <div class="onglet-section active">

                <div class="onglet-img">
                    <img src="${pageContext.request.contextPath}/assets/img/home.png" alt="Accueil" class="icon">
                </div>

                <div class="onglet-titre">
                    <a href="#" class="a">Accueil</a>
                </div>

            </div>

            <div class="onglet-section">

                <div class="onglet-img">
                    <img src="${pageContext.request.contextPath}/assets/img/recom.png" alt="Recommandations" class="icon">
                </div>

                <div class="onglet-titre">
                    <a href="#" class="a">Recommandations</a>
                </div>

            </div>

            <div class="onglet-section">

                <div class="onglet-img">
                    <img src="${pageContext.request.contextPath}/assets/img/hist.png" alt="Historique" class="icon">
                </div>

                <div class="onglet-titre">
                    <a href="#" class="a">Historiques</a>
                </div>

            </div>

            <div class="onglet-section">

                <div class="onglet-img">
                    <img src="${pageContext.request.contextPath}/assets/img/Project.png" alt="Projet" class="icon">
                </div>

                <div class="onglet-titre">
                    <a href="#" class="a">Mes projets</a>
                </div>

            </div>

        </nav>

    </aside>


</body>
</html>
