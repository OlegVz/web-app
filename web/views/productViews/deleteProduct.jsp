<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete product</title>
</head>
<body>
<h1>Shop web app</h1>
<h2>Delete product</h2>
<%
    if (request.getAttribute("deletedProduct") != null) {
        out.println("<p>Product '" + request.getAttribute("deletedProduct") + "' deleted!</p>");
    }
%>
<form action="/delete-product" method="post">
    <label>ID:
        <input type="text" name="id">
    </label>
    <button type="submit">Submit</button>
</form>
<button onclick="location.href='/index.html'">Back to main</button>
</body>
</html>