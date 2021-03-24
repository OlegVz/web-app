<%@ page import="app.entities.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New order</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Add order</h2>
<%
    Order order = (Order) request.getAttribute("order");
    if (order != null) {
        out.println("<p>Order: '" + "ID: " + order.getId() + ", user id: " + order.getUserId() +
                ", status: " + order.getStatus() + ", created at: " + order.getCreatedAt() + "' added!</p>");
    }
%>
<form action="/add-new-order" method="post">
    <label>User ID:
        <input type="text" name="userId"><br/>
    </label>

    <label>Order status:
        <input type="text" name="status"><br/>
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>

