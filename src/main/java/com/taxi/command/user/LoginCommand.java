package com.taxi.command.user;

import com.taxi.command.Command;
import com.taxi.domain.User;
import com.taxi.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = (String) request.getAttribute("email");
        final String password = (String) request.getAttribute("password");
        final User user = userService.login(email, password);
        final HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "view/profile.jsp";
    }
}
