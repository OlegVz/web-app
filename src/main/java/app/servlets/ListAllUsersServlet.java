package app.servlets;

import app.repositry.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ListAllUsersServlet extends HttpServlet {

    private UserRepository userRepository;

    public ListAllUsersServlet() {
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("Method GET from ListAllUsersServlet");
        writer.println("User" + userRepository.getUser());
    }
}
