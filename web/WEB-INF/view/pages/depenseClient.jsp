<%--
  Created by IntelliJ IDEA.
  User: Dialla COULOUBALY
  Date: 04/06/2026
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%
    List<Depense> c = (List<Depense>) request.getAttribute("depense");
%>
<head>
    <title>Title</title>
</head>
<body>
<%
    double total = 0;

    for(Depense d : c){
        total += d.getMontant();
    }
%>

<p>Total des dépenses : <%= total %> FCFA</p>
</body>
</html>
