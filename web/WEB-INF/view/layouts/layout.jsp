<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25/05/2026
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>layout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout.css">
</head>
<body>
    <div class="layout">


        <jsp:include page="sidebar.jsp"/>


        <main class="main-content">


            <jsp:include page="topbar.jsp"/>


            <section class="dynamic-content">


                <jsp:include page="../pages/${pageContent}" />

            </section>

        </main>

    </div>

</body>
</html>
