package com.my.web.controller.command.user;

import com.my.persistence.entity.User;
import com.my.service.ReportService;
import com.my.web.controller.command.Command;
import com.my.web.dto.ReportDTO;
import com.my.web.dto.ReportFormError;

import javax.servlet.http.HttpServletRequest;

public class UserReportApplyCommand implements Command {
    private final ReportService reportService = ReportService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equalsIgnoreCase("GET")) {
            return processGetRequest(request);
        } else {
            return processPostRequest(request);
        }
    }

    private String processGetRequest(HttpServletRequest request) {

        return "/WEB-INF/user/report-form";
    }

    private String processPostRequest(HttpServletRequest request) {

        ReportFormError formError = (ReportFormError) request.getAttribute("errorReportFormDTO");

        if (formError.hasErrors()) {
            request.setAttribute("fields", formError);
            return "/WEB-INF/user/report-form";
        }

        ReportDTO report = (ReportDTO) request.getAttribute("reportDTO");
        User user = (User) request.getSession().getAttribute("user");
        report.setUserId(user.getId());

        reportService.applyNewReport(report);

        return "redirect:/user/reports";
    }


    @Override
    public String toString() {
        return "UserReportApplyCommand";
    }
}
