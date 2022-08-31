package com.my.web.controller.command.user;

import com.my.service.ReportService;
import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UserReportDeleteCommand implements Command {

    private final ReportService reportService = ReportService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        reportService.deleteReportById(Long.parseLong(request.getParameter("reportId")));

        return "redirect:/user/reports";
    }
}