<%@ page import="app.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders list</title>
</head>
<body>
<h1>Shop web app!</h1>
<h2>Orders list</h2>
<%
    List<Order> orderList = (List<Order>) request.getAttribute("orderList");

    if (!orderList.isEmpty()) {
        out.println("<ui>");
        for (Order order : orderList) {
            out.println("<li> id: " + order.getId() + ", user ID: " + order.getUserId() +
                    ", status: " + order.getStatus() + ", created at: " + order.getCreatedAt() + " </li>");
        }
        out.println("</ui>");
    } else {
        out.println("<p>There are no orders!</p>");
    }
%>
<form action="/list-users" method="get">

</form>
<button onclick="location.href='/index.html'">Back to main</button>
</div>
</body>
</html>

