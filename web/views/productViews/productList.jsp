<%@ page import="app.entities.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<h1>Shop web app!</h1>
<h2>Users list</h2>
<%
    List<Product> productList = (List<Product>) request.getAttribute("productList");

    if (!productList.isEmpty()) {
        out.println("<ui>");
        for (Product product : productList) {
            out.println("<li> id: " + product.getId() + ", product: " + product.getName() +
                    ", price: " + product.getPrice() + ", status: " + product.getProductStatus() +
                    ", created at: " + product.getCreatedAt() + " </li>");
        }
        out.println("</ui>");
    } else {
        out.println("<p>There are no products!</p>");
    }
%>
<form action="/list-users" method="get">

</form>
<button onclick="location.href='/index.html'">Back to main</button>
</div>
</body>
</html>
