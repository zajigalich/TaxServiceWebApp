package com.my.web.controller.command.app;

import com.my.exception.UserNotFoundException;
import com.my.exception.WrongPasswordException;

import com.my.persistence.entity.User;
import com.my.persistence.entity.UserRole;
import com.my.service.UserService;

import com.my.web.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

        private static final Logger log = Logger.getLogger(LoginCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Login request  " + request.getRequestURI() + "  " + request.getMethod());

        if (request.getMethod().equalsIgnoreCase("get")) {
            return "/login";
        }

        ServletContext servletContext = request.getServletContext();
        UserService userService = (UserService) servletContext.getAttribute("userService");


        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        log.info("Login process with data: " + email +", " + password);

        if(email.equals("inspector") && password.equals("register")){
            return "redirect:/register";
        }
        try {
            User user = userService.validateLoginData(email, password);

            session.setAttribute("user", user);

            log.info("User logged in + " + user);

            if (user.getUserRole().equals(UserRole.USER))
                return "redirect:/user/reports";
            else if (user.getUserRole().equals(UserRole.INSPECTOR))
                return "redirect:/inspector/reports";


        } catch (UserNotFoundException | WrongPasswordException e) {
            log.error("Login exception");
            request.setAttribute("exceptionLogin", e.getMessage());
            request.setAttribute("email", email);
        }
        return "login";
    }

    @Override
    public String toString() {
        return "LoginCommand";
    }
}
