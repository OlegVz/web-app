package app.servlets.userServlets;

import app.entities.User;
import app.repositry.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ListAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.list();
        req.setAttribute("userList", users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/userViews/userList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
