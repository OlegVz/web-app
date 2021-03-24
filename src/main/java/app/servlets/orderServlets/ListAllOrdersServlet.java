package app.servlets.orderServlets;

import app.entities.Order;
import app.repositry.OrderRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ListAllOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderRepository orderRepository = new OrderRepository();
        List<Order> orders = orderRepository.list();
        req.setAttribute("orderList", orders);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderServlets/orderList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
