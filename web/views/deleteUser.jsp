<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Delete user</h2>
<%
    if (request.getAttribute("deletedUser") != null) {
        out.println("<p>User '" + request.getAttribute("deletedUser") + "' deleted!</p>");
    }
%>
<form action="/delete-user" method="post">
    <label>ID:
        <input type="text" name="id">
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>
