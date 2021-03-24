package app.servlets.orderItemServlets;

import app.entities.OrderItem;
import app.repositry.OrderItemRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteOrderItemById extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderItemServlets/deleteOrderItem.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("productId"));
        long orderId = Long.parseLong(req.getParameter("orderId"));

        OrderItemRepository orderItemRepository = new OrderItemRepository();
        OrderItem orderItem = orderItemRepository.deleteOrderItemById(productId, orderId);

        req.setAttribute("deletedOrderItem", orderItem);
        doGet(req, resp);
    }
}
