<%@ page import="app.entities.OrderItem" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New orderItem</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Add orderItem</h2>
<%
    OrderItem orderItem = (OrderItem) request.getAttribute("orderItem");
    if (orderItem != null) {
        out.println("<p>OrderItem '" + orderItem + "' added!</p>");
    }
%>
<form action="/add-new-orderItem" method="post">
    <label>Product ID:
        <input type="text" name="productId"><br/>
    </label>

    <label>Order ID:
        <input type="text" name="orderId"><br/>
    </label>

    <label>Quantity:
        <input type="text" name="quantity"><br/>
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>
