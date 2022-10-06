package com.my.web.controller.command.app;

import com.my.exception.UserAlreadyExistsException;
import com.my.persistence.entity.EntrepreneurType;
import com.my.persistence.entity.User;
import com.my.persistence.entity.UserRole;
import com.my.service.RegistrationService;
import com.my.web.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterInspectorCommand implements Command {

    private static final Logger log = Logger.getLogger(RegisterInspectorCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getMethod().equalsIgnoreCase("get")) {
            log.info("Request to registration page");
            return "/register";
        }

        User inspector = User.builder()
                .userRole(UserRole.INSPECTOR)
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();
        log.info("User entity has been created  " + inspector);
        try {

            RegistrationService.registerInspector(inspector);
            log.info("User has been registered: " + inspector);

        } catch (UserAlreadyExistsException e) {
            log.error("User with email(" + request.getParameter("email") + ") is already exist");
            e.printStackTrace();
            return "/register";
        }

        return "/login";
    }
}
