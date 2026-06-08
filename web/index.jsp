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
    <div class="etapes">
        <h5 class="etapes-titre">De l'Idée à la Réalisation</h5>


        <section class="icone_container">

        <div class="icone_fa">
            <i class="fa-regular fa-lightbulb"></i>
            <p>Idée</p>
        </div>

        <div class="lignes"></div>

        <div class="icone_fa">
            <i class="fa-regular fa-calendar"></i>
            <p>Planification</p>
        </div>

        <div class="lignes"></div>

        <div class="icone_fa">
            <i class="fa-solid fa-gear"></i>
            <p>Exécution</p>
        </div>

        <div class="lignes"></div>

        <div class="icone_fa">
            <i class="fa-regular fa-circle-check"></i>
            <p>Réalisation</p>
        </div>


    </section>
    </div>
</header>
<main>
    <section class="left">
        <h1>Advisor</h1>
        <p class="sous-titre">
            Accompagnez vos idées, suivez vos projets et
            <span class="bleu">réalisez </span> vos ambitions.
        </p>
        <p>
            Advisor est une application qui aide les personnes à choisir des projets adaptés à leur profil, à recevoir des recommandations intelligentes et à suivre leur progression
            jusqu’à la réalisation complète, tout en gérant le budget et les différentes étapes.
        </p>

        <div class="btn-container">
            <a href="connexion" class="btn btn-connecter">Se connecter</a>
            <a href="inscription" class="btn btn-inscrire">S'inscrire</a>
        </div>
    </section>
    <section class="right">
        <img src="${pageContext.request.contextPath}/assets/img/pre_accueil.png" alt="image du pre_accueil"/>
    </section>
</main>
<footer>
    <section class="icone_fas">
        <i class="fa-solid fa-star"></i>
        <div class="texte">
            <h1>Recommandez</h1>
            <p>Trouvez des projets adaptés à votre profil et vos ressources.</p>
        </div>
    </section>

    <section class="icone_fas">
        <i class="fa-solid fa-user-plus"></i>
        <div class="texte">
            <h1>Suivie</h1>
            <p>Suivez l’évolution de vos projets étape par étape.</p>
        </div>
    </section>

    <section class="icone_fas">
        <i class="fa-solid fa-circle-check"></i>
        <div class="texte">
            <h1>Réaliser</h1>
            <p>Atteignez vos objectifs et concrétisez vos idées.</p>
        </div>
    </section>

</footer>
</body>
</html>

