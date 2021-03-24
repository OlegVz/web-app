<%@ page import="app.entities.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OrderItems list</title>
</head>
<body>
<h1>Shop web app!</h1>
<h2>OrderItems list</h2>
<%
    List<OrderItem> orderItemList = (List<OrderItem>) request.getAttribute("orderItemsList");

    if (!orderItemList.isEmpty()) {
        out.println("<ui>");
        for (OrderItem orderItem : orderItemList) {
            out.println("<li> id: " + orderItem + " </li>");
        }
        out.println("</ui>");
    } else {
        out.println("<p>There are no orderItems!</p>");
    }
%>
<form action="/list-orderItems" method="get">

</form>
<button onclick="location.href='/index.html'">Back to main</button>
</div>
</body>
</html>
