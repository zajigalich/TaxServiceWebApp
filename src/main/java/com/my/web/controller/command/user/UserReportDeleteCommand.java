package com.my.web.controller.command.user;

import com.my.service.ReportService;
import com.my.web.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserReportDeleteCommand implements Command {
    private static final Logger log = Logger.getLogger(UserReportDeleteCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ServletContext servletContext = request.getServletContext();
        ReportService reportService = (ReportService) servletContext.getAttribute("reportService");


        log.info(Long.parseLong(request.getParameter("reportId")));
        reportService.deleteReportById(Long.parseLong(request.getParameter("reportId")));

        return "redirect:/user/reports";
    }
}