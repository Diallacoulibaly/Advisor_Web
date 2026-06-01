<!--
Created by IntelliJ IDEA.
User: Dialla COULOUBALY
Date: 28/05/2026
Time: 19:37
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pré Accueil</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pre_accueil.css">
</head>


<body>
<header>
    <img src="${pageContext.request.contextPath}/assets/img/logo_preaccueil.png" alt="image du logo"/>
    <section>
        <i class="fa-regular fa-lightbulb"></i>
        <i class="fa-regular fa-calendar"></i>
        <i class="fa-solid fa-gear"></i>
        <i class="fa-regular fa-circle-check"></i>
    </section>
</header>
<main>
    <section class="left">
        <h1>Advisor</h1>
        <p>
            Advisor aide les personnes à choisir des projets adaptés à leur profil, à recevoir des recommandations intelligentes et à suivre leur progression
            jusqu’à la réalisation complète, tout en gérant le budget et les différentes étapes.
        </p>

        <div class="btn-container">
            <a href="#" class="btn btn-connecter">Se connecter</a>
            <a href="#" class="btn btn-inscrire">S'inscrire</a>
        </div>
    </section>
    <section class="right">
        <img src="${pageContext.request.contextPath}/assets/img/pre_accueil.png" alt="image du pre_accueil"/>
    </section>
</main>
<footer>
    <section>

        <i></i>
        <h1>Recommandez</h1>
        <p>Trouvez des projets adaptés à votre profil et vos ressources.</p>

    </section>

    <section>
        <i></i>
        <h1>Suiviez</h1>
        <p>Suivez l’évolution de vos projets étape par étape.</p>
    </section>

    <section>

        <i></i>
        <h1>Réalisez</h1>
        <p>Atteignez vos objectives et concrétisez vos idées.</p>
    </section>
</footer>
</body>
</html>

