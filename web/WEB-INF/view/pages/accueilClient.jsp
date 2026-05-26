<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25/05/2026
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/accueilClient.css">
    </head>
<body>
<div class="cards-container">

</div>

    <section class="content">
        <div class="cards-container">

            <!-- CARD 1 -->
            <div class="dashboard-card progress-card">

                <h2>Ma progression</h2>

                <div class="card-value">
                    25 <span>%</span>
                </div>

            </div>


            <div class="dashboard-card depense-card">

                <h2>Dépenses faites</h2>

                <div class="money-content">

                    <div class="card-value money">
                        10 000 F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>


            <div class="dashboard-card preview-card">

                <h2>Dépenses prévu</h2>

                <div class="money-content">

                    <div class="card-value money">
                        20 000 F CFA
                    </div>

                    <img src="${pageContext.request.contextPath}/assets/img/money.png" alt="money" class="money-icon">

                </div>

            </div>


        </div>
        <div class="project-card">



            <div class="project-header">

                <div>

                    <h2 class="project-title">
                        Application de gestion agricole
                    </h2>

                    <p class="project-description">
                        Plateforme permettant aux utilisateurs
                        de suivre leurs activités agricoles,
                        leurs dépenses et leurs revenus.
                    </p>

                </div>

                <div class="project-status">
                    En cours
                </div>

            </div>



            <div class="project-infos">

                <div class="info-box">

                    <h3>Durée</h3>

                    <p>6 mois</p>

                </div>

                <div class="info-box">

                    <h3>Étapes</h3>

                    <p>12 étapes</p>

                </div>

                <div class="info-box">

                    <h3>Domaine</h3>

                    <p>Agriculture</p>

                </div>

                <div class="info-box">

                    <h3>Budget</h3>

                    <p>150 000 F CFA</p>

                </div>

            </div>

        </div>

    </section>

</body>
</html>
