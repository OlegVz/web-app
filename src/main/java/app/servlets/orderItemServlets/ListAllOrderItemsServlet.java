package app.servlets.orderItemServlets;

import app.entities.Order;
import app.entities.OrderItem;
import app.repositry.OrderItemRepository;
import app.repositry.OrderRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ListAllOrderItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        List<OrderItem> orderItems = orderItemRepository.list();
        req.setAttribute("orderItemsList", orderItems);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderItemServlets/orderItemsList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
