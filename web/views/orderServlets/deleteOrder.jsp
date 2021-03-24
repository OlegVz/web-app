<%@ page import="app.entities.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete order</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Delete order</h2>
<%
    Order deletedOrder = (Order) request.getAttribute("deletedOrder");
    if (deletedOrder != null) {
        out.println("<p>Order: '" + deletedOrder + "' deleted!</p>");
    }
%>
<form action="/delete-order" method="post">
    <label>ID:
        <input type="text" name="id">
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>