package app.servlets.orderServlets;

import app.entities.Order;
import app.repositry.OrderRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateNewOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderServlets/addOrder.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        String status = req.getParameter("status");
        String createdAt = LocalDateTime.now().toString();

        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(status);
        order.setCreatedAt(createdAt);

        OrderRepository orderRepository = new OrderRepository();
        Order save = orderRepository.save(order);

        req.setAttribute("order", save);
        doGet(req, resp);
    }
}
