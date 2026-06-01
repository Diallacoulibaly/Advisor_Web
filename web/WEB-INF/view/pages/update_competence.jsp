<%@ page import="main.java.model.classes.Competence" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Competence competence = (Competence) request.getAttribute("competence");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier une compétence</title>
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
            padding: 11px; background: #f59e0b;
            color: #fff; border: none; border-radius: 8px;
            font-size: 15px; cursor: pointer;
        }
        .btn-submit:hover { background: #d97706; }
        .btn-retour { display: block; text-align: center; margin-top: 12px; color: #1e3a8a; font-size: 13px; text-decoration: none; }
    </style>
</head>
<body>

<div class="card">
    <h1>Modifier la compétence</h1>

    <form action="competences?action=modifier" method="post">
        <!-- ID caché pour savoir quelle compétence modifier -->
        <input type="hidden" name="id" value="<%= competence.getId() %>">

        <label for="nom">Nom de la compétence</label>
        <input type="text" id="nom" name="nom" value="<%= competence.getNom() %>" required>

        <button type="submit" class="btn-submit">Enregistrer les modifications</button>
    </form>

    <a href="competences" class="btn-retour">← Retour à la liste</a>
</div>

</body>
</html>
