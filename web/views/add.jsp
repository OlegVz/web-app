<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 23.03.21
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New user</title>
</head>
<body>
<div>
    <h1>Shop web app</h1>
</div>

<div>
        <%
                if (request.getAttribute("userEmail") != null) {
                    out.println("<p>User '" + request.getAttribute("userEmail") + "' added!</p>");
                }
            %>
    <div>
        <div>
            <h2>Add user</h2>
        </div>

        <form method="post">
            <label>Name:
                <input type="text" name="email"><br/>
            </label>

            <label>Password:
                <input type="password" name="password"><br/>
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>

    <div>
        <button onclick="location.href='/'">Back to main</button>
    </div>
</body>
</html>
