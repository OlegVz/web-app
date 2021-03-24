
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New user</title>
</head>
<body>
    <h1>Shop web app</h1>
    <h2>Add user</h2>
    <%
        if (request.getAttribute("userEmail") != null) {
            out.println("<p>User '" + request.getAttribute("userEmail") + "' added!</p>");
        }
    %>
    <form action="/add-new-user" method="post">
        <label>Name:
            <input type="text" name="email"><br/>
        </label>

        <label>Password:
            <input type="password" name="password"><br/>
        </label>
        <button type="submit">Submit</button>
    </form>
    <button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>
