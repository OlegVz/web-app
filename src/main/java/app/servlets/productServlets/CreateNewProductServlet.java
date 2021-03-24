package app.servlets.productServlets;

import app.entities.Product;
import app.entities.ProductStatus;
import app.repositry.ProductRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateNewProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/productViews/addProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Long price = Long.parseLong(req.getParameter("price"));
        String productStatus = req.getParameter("productStatus");

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setProductStatus(ProductStatus.valueOf(productStatus));
        product.setCreatedAt(LocalDateTime.now());

        ProductRepository productRepository = new ProductRepository();
        Product save = productRepository.save(product);

        req.setAttribute("productName", save.getName());
        doGet(req, resp);
    }
}
