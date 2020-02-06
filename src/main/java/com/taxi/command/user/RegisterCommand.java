package com.taxi.command.user;

import com.taxi.command.Command;
import com.taxi.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
   /* private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }*/

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
