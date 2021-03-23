<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 23.03.21
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<div>
    <h1>Shop web app!</h1>
</div>

<div>
    <div>
        <div>
            <h2>Users</h2>
        </div>
        <%
            List<String> emails = (List<String>) request.getAttribute("userEmails");

            if (emails != null && !emails.isEmpty()) {
                out.println("<ui>");
                for (String email : emails) {
                    out.println("<li>" + email + "</li>");
                }
                out.println("</ui>");
            } else out.println("<p>There are no users yet!</p>");
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
