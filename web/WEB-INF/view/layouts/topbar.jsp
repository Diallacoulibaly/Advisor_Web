<%@ page import="main.java.model.classes.Utilisateur" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25/05/2026
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Utilisateur user = (Utilisateur) request.getAttribute("user");%>
<html>
<head>
    <title>topbar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/topbar.css">


</head>
<body>
    <header class="topbar">

        <div class="profil-container">

            <img src="${pageContext.request.contextPath}/assets/img/profil.png" alt="Profil" class="profil">


            <div>
                <h2 class="user-name">${username}</h2>
                <h4 class="role-text">${role}</h4>
            </div>


        </div>

    </header>
</body>
</html>
