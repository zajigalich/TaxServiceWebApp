package com.my.web.controller.command.user;

import com.my.service.ReportService;
import com.my.web.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserReportDeleteCommand implements Command {
    private static final Logger log = Logger.getLogger(UserReportDeleteCommand.class);

    private final ReportService reportService = ReportService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        log.info(Long.parseLong(request.getParameter("reportId")));
        reportService.deleteReportById(Long.parseLong(request.getParameter("reportId")));

        return "redirect:/user/reports";
    }
}