package app.servlets.orderItemServlets;

import app.entities.OrderItem;
import app.repositry.OrderItemRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CreateNewOrderItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderItemServlets/addOrderItem.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("productId"));
        long orderId = Long.parseLong(req.getParameter("orderId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setOrderId(orderId);
        orderItem.setQuantity(quantity);

        OrderItemRepository orderItemRepository = new OrderItemRepository();
        OrderItem save = orderItemRepository.save(orderItem);

        req.setAttribute("orderItem", save);
        doGet(req, resp);
    }
}
