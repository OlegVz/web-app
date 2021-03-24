<%@ page import="app.entities.OrderItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete orderItem</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Delete orderItem</h2>
<%
    OrderItem deletedOrderItem = (OrderItem) request.getAttribute("deletedOrderItem");
    if (deletedOrderItem != null) {
        out.println("<p>OrderItem: '" + deletedOrderItem + "' deleted!</p>");
    }
%>
<form action="/delete-orderItem" method="post">
    <label>Product ID:
        <input type="text" name="productId"><br/>
    </label>

    <label>Order ID:
        <input type="text" name="orderId"><br/>
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>