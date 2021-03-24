<%@ page import="java.util.List" %>
<%@ page import="app.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<h1>Shop web app!</h1>
<h2>Users list</h2>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");

    if (!userList.isEmpty()) {
        out.println("<ui>");
        for (User user : userList) {
            out.println("<li> id: " + user.getId() + ", email: " + user.getEmail() + "</li>");
        }
        out.println("</ui>");
    } else {
        out.println("<p>There are no users!</p>");
    }
%>
<form action="/list-users" method="get">

</form>
    <button onclick="location.href='/index.html'">Back to main</button>
</div>
</body>
</html>
