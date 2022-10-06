package com.my.web.controller;

import com.my.web.controller.command.*;
import com.my.web.controller.command.app.*;
import com.my.web.controller.command.inspector.InspectorReportViewCommand;
import com.my.web.controller.command.inspector.InspectorReportsCommand;
import com.my.web.controller.command.inspector.InspectorStatisticCommand;
import com.my.web.controller.command.inspector.InspectorUserViewCommand;
import com.my.web.controller.command.user.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/", name = "FrontControllerServlet")
public class FrontControllerServlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    private static final Logger log = Logger.getLogger(FrontControllerServlet.class);

    @Override
    public void init(ServletConfig config) {
        //config.getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("", new MainCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());

        commands.put("user/reports", new UserReportsCommand());
        commands.put("user/user-info", new UserInfoCommand());
        commands.put("user/report-form", new UserReportApplyCommand());
        commands.put("user/report-edit", new UserReportEditCommand());
        commands.put("user/report-delete", new UserReportDeleteCommand());
        commands.put("user/report-save", new UserReportSaveJsonCommand());

        commands.put("inspector/reports", new InspectorReportsCommand());
        commands.put("inspector/statistic", new InspectorStatisticCommand());
        commands.put("inspector/user-view", new InspectorUserViewCommand());
        commands.put("inspector/report-view", new InspectorReportViewCommand());
        commands.put("register", new RegisterInspectorCommand());

        commands.put("error", new ExceptionCommand());

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

        String path = request.getRequestURI().replaceFirst("/", "");

        Command command = commands.getOrDefault(path.trim(), (req, res) -> "/error/error404");
        log.debug("Command: " + command.toString());

        String page = "/error/error500";

        try {

            page = command.execute(request, response);
            //log.info("Page after executing command  " + page);
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