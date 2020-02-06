package com.taxi.controller;

import com.taxi.domain.Role;
import com.taxi.domain.User;
import com.taxi.injector.ApplicationInjector;
import com.taxi.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private final UserService userService;

    public UserServlet() {
        ApplicationInjector injector = ApplicationInjector.getInstance();
        this.userService = injector.getUserService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final User user = (User) req.getSession().getAttribute("user");
        final List<User> users = userService.findAll(1);
        req.setAttribute("users", users);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
