<%--
  Created by IntelliJ IDEA.
  User: CHITAN FOUNE MALLE
  Date: 31/05/2026
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ClientCompetence</title>
</head>
<body>
<form method="post">

    <label>Client :</label>
    <select name="clientId">
        <c:forEach items="${clients}" var="client">
            <option value="${client.id}">
                    ${client.nom}
            </option>
        </c:forEach>
    </select>

    <label>Compétence :</label>
    <select name="competenceId">
        <c:forEach items="${competences}" var="competence">
            <option value="${competence.id}">
                    ${competence.libelle}
            </option>
        </c:forEach>
    </select>

    <button type="submit">Enregistrer</button>

</form>
</body>
</html>
