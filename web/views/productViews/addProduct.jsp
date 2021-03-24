<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New product</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Add product</h2>
<%
    if (request.getAttribute("productName") != null) {
        out.println("<p>Product '" + request.getAttribute("productName") + "' added!</p>");
    }
%>
<form action="/add-new-product" method="post">
    <label>Product name:
        <input type="text" name="name"><br/>
    </label>

    <label>Product price:
        <input type="text" name="price"><br/>
    </label>

    <label>Product status (IN_STOCK, OUT_OF_STOCK, RUNNING_LOW):
        <input type="text" name="productStatus"><br/>
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>
