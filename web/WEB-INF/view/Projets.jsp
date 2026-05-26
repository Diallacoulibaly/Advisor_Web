<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<body>
    <h1>Liste des Projets</h1>
    <table border="1">
        <tr>
            <th>Titre</th><th>Niveau</th><th>Budget Max</th>
        </tr>
        <c:forEach var="p" items="${projets}">
            <tr>
                <td>${p.titre}</td>
                <td>${p.niveau}</td>
                <td>${p.budgetMax} FCFA</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>