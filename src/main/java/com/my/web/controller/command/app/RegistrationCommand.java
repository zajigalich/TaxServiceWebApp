package com.my.web.controller.command.app;

import com.my.exception.UserAlreadyExistsException;
import com.my.persistence.entity.EntrepreneurType;
import com.my.persistence.entity.User;
import com.my.service.RegistrationService;
import com.my.web.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class RegistrationCommand implements Command {

    private static final Logger log = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.info("Registration request: " + request.getRequestURI() + " request method" + request.getMethod());
        if (request.getMethod().equalsIgnoreCase("get")) {
            log.info("Request to registration page");
            return "/registration";
        }
        User user = User.builder()
                .name(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .tin(request.getParameter("tin"))
                .entrepreneurType(EntrepreneurType.valueOf(request.getParameter("personality")))
                .address(request.getParameter("address"))
                .build();
        log.info("User entity has been created  " + user);
        try {

            RegistrationService.registerUser(user);
            log.info("User has been registered: " + user);

        } catch (UserAlreadyExistsException e) {
            log.error("User with email(" + request.getParameter("email") + ") is already exist");
            e.printStackTrace();
            return "/registration";
        }

        return "/login";
    }

    @Override
    public String toString() {
        return "RegistrationCommand";
    }
}
