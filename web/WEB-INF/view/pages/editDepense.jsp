<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourpackage.entity.Depense" %>

<%
    Depense depense = (Depense) request.getAttribute("depense");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Modifier dépense</title>

    <style>
        body {
            font-family: Arial;
            margin: 20px;
        }

        .form-container {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        input, textarea {
            width: 100%;
            margin-bottom: 10px;
            padding: 8px;
        }

        button {
            background: #27ae60;
            color: white;
            padding: 10px;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            background: #219150;
        }

        .back {
            display: inline-block;
            margin-bottom: 10px;
            text-decoration: none;
        }
    </style>

</head>

<body>

<h2>✏ Modifier la dépense</h2>

<a class="back" href="depenses?action=liste&idActivite=<%= depense.getActivite().getId() %>">
    ⬅ Retour
</a>

<div class="form-container">

    <form action="depenses" method="post">

        <!-- ACTION -->
        <input type="hidden" name="action" value="modifier">

        <!-- ID DEPENSE -->
        <input type="hidden" name="idDepense" value="<%= depense.getIdDepense() %>">

        <!-- ID ACTIVITE -->
        <input type="hidden" name="idActivite" value="<%= depense.getActivite().getId() %>">

        <!-- MONTANT -->
        <label>Montant</label>
        <input type="number" name="montant"
               value="<%= depense.getMontant() %>" required>

        <!-- DESCRIPTION -->
        <label>Description</label>
        <textarea name="description" required>
            <%= depense.getDescription() %>
        </textarea>

        <button type="submit">Modifier</button>

    </form>

</div>

</body>
</html>