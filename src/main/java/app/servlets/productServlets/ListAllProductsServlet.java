package app.servlets.productServlets;

import app.entities.Product;
import app.repositry.ProductRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ListAllProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.list();

        req.setAttribute("productList", products);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/productViews/productList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
