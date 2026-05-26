<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Inscription</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/inscription.css">


</head>

<body>

    <div class="container">



        <div class="left-section">

            <h1>Creer un compte</h1>

            <form method="post">

                <div class="input-group">
                    <input type="text" placeholder="Prenom" name="prenom">
                </div>

                <div class="input-group">
                    <input type="text" placeholder="Nom" name="nom">
                </div>

                <div class="input-group">
                    <input type="text" placeholder="Telephone" name="telephone">
                </div>

                <div class="input-group">
                    <input type="email" placeholder="Entre votre email" name="email">
                </div>

                <div class="input-group password-group">

                    <input type="password" placeholder="Mot de passe" name="password">

                    <span class="eye-icon">👁</span>

                </div>

                <button type="submit" class="btn-inscription">
                    Inscription
                </button>

            </form>

        </div>



        <div class="right-section">

            <div class="welcome-content">

                <h2>Bienvenue sur Advisor</h2>

                <p>
                    Connectez-vous si vous avez déjà un compte
                </p>

                <button class="btn-connexion">
                    Connection
                </button>

            </div>

        </div>

    </div>

</body>

</html>