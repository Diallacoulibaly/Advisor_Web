<%@ page import="java.util.List" %>
<%@ page import="main.java.model.classes.Domaine" %><%--
  Created by IntelliJ IDEA.
  User: kalandew20
  Date: 22/05/2026
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%List <Domaine> domaines=(List<Domaine>) request.getAttribute("domaines");%>
<html>
<head>

    <title>La liste des domaines</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/domaine.css">
</head>

<body>


<a href="domaines?actions=addDomaine" class="btn-ajout">
    <button>Ajouter un Domaine</button>
</a>
<h1>La liste des domaines</h1>
<h1 style="color:red">TEST CSS</h1>
<table border="1">
    <tr>

        <th>ID</th>
        <th>Nom</th>
        <th>Actions</th>

    </tr>
<% for (int i=0 ; i<domaines.size();i++){
%>
    <tr>

        <td>
            <%= i+1 %>
        </td>

        <td>
            <%= domaines.get(i).getDomaine() %>
        </td>

        <td>
            <a class="edit" href="domaines?actions=editDomaine&id=<%= domaines.get(i).getId() %>">

                Modifier

            </a>


            <a class="delete" href="domaines?actions=deleteDomaine&id=<%= domaines.get(i).getId() %>">

                Supprimer

            </a>

        </td>

    </tr>

    <%
        }
    %>


</table>

</body>
</html>
