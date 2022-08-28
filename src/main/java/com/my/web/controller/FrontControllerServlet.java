package com.my.web.controller;

import com.my.web.controller.command.*;
import com.my.web.controller.command.app.LoginCommand;
import com.my.web.controller.command.app.MainCommand;
import com.my.web.controller.command.app.RegistrationCommand;
import com.my.web.controller.command.inspector.InspectorReportsCommand;
import com.my.web.controller.command.user.UserReportsCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * The main servlet for processing requests, actions and displaying pages
 */
@WebServlet(value = "/", name = "FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    private static final Logger log = Logger.getLogger(FrontControllerServlet.class);

    /**
     * Holder for all commands
     */
    @Override
    public void init(ServletConfig config) {
        config.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("", new MainCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("login", new LoginCommand());
        //commands.put("logout", new LogoutCommand());

        commands.put("user/reports", new UserReportsCommand());

        commands.put("inspector/reports", new InspectorReportsCommand());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI()
                .replaceFirst("/WEB-INF", "")
                .replaceFirst("/", "");

        //String path=request.getRequestURI();
        //path=path.replaceAll(".*/", "");

        Command command = commands.getOrDefault(path.trim(), (c) -> "/WEB-INF/error/error404");
        log.debug("Command: " + command.toString());
        String page = "/WEB-INF/error/error500";

        try {

            page = command.execute(request);
            log.info("Page after executing command  " + page);
        } catch (Exception exception) {
            log.error("Exception " + exception.getMessage());
            request.setAttribute("exception", exception.getMessage());
        }

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page + ".jsp").forward(request, response);
        }
    }
}