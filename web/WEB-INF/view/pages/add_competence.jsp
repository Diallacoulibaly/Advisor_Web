<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter une compétence</title>
    <style>
        body { font-family: 'Poppins', sans-serif; background: #ebebed; display: flex; align-items: center; justify-content: center; min-height: 100vh; }
        .card { background: #fff; padding: 36px 40px; border-radius: 16px; box-shadow: 0 8px 30px rgba(0,0,0,0.1); width: 400px; }
        h1 { color: #1e3a8a; font-size: 22px; margin-bottom: 24px; }
        label { display: block; font-size: 13px; color: #555; margin-bottom: 6px; }
        input[type="text"] {
            width: 100%; padding: 10px 14px;
            border: 1px solid #d1d5db; border-radius: 8px;
            font-size: 14px; outline: none;
            transition: border 0.2s;
            box-sizing: border-box;
        }
        input[type="text"]:focus { border-color: #1e3a8a; }
        .btn-submit {
            margin-top: 20px; width: 100%;
            padding: 11px; background: #1e3a8a;
            color: #fff; border: none; border-radius: 8px;
            font-size: 15px; cursor: pointer;
        }
        .btn-submit:hover { background: #1a3275; }
        .btn-retour { display: block; text-align: center; margin-top: 12px; color: #1e3a8a; font-size: 13px; text-decoration: none; }
    </style>
</head>
<body>

<div class="card">
    <h1>Ajouter une compétence</h1>

    <form action="competences?action=ajouter" method="post">
        <label for="nom">Nom de la compétence</label>
        <input type="text" id="nom" name="nom" placeholder="Ex : Java, Photoshop, Marketing..." required>

        <button type="submit" class="btn-submit">Ajouter</button>
    </form>

    <a href="competences" class="btn-retour">← Retour à la liste</a>
</div>

</body>
</html>
