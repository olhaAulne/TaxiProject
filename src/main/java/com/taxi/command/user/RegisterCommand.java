package com.taxi.command.user;

import com.taxi.command.Command;
import com.taxi.domain.User;
import com.taxi.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        final String email = (String) request.getAttribute("email");
        final String name = (String) request.getAttribute("name");
        final String surname = (String) request.getAttribute("surname");
        final String password1 = (String) request.getAttribute("password1");
        final String password2 = (String) request.getAttribute("password2");

        if (!Objects.equals(password1, password2)) {
            return "view/register.jsp";
        }

        final User user = User.builder()
                .withEmail(email)
                .withName(name)
                .withSurname(surname)
                .withPassword(password1)
                .build();

        userService.register(user);

        return "view/login.jsp";
    }
}
