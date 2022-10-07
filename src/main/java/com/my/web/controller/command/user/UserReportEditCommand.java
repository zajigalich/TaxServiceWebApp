package com.my.web.controller.command.user;

import com.my.persistence.entity.ReportStatus;
import com.my.service.ReportService;
import com.my.web.controller.command.Command;
import com.my.web.dto.ReportDTO;
import com.my.web.dto.ReportFormError;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserReportEditCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        if (request.getMethod().equals("GET")) {
            return processGetRequest(request);
        } else {
            return processPostRequest(request);
        }
    }

    private String processGetRequest(HttpServletRequest request) {

        ServletContext servletContext = request.getServletContext();
        ReportService reportService = (ReportService) servletContext.getAttribute("reportService");


        Long reportId = Long.valueOf(request.getParameter("reportId"));
        ReportDTO reportById = reportService.getReportById(reportId);

        request.getSession().setAttribute("reportById", reportById);

        return "/WEB-INF/user/report-edit";
    }

    private String processPostRequest(HttpServletRequest request) {

        ServletContext servletContext = request.getServletContext();
        ReportService reportService = (ReportService) servletContext.getAttribute("reportService");


        ReportFormError formError = (ReportFormError) request.getAttribute("errorReportFormDTO");

        if (formError.hasErrors()) {
            request.setAttribute("fields", formError);
            return "/WEB-INF/user/report-form";
        }

        HttpSession session = request.getSession();

        ReportDTO initialReport = (ReportDTO) session.getAttribute("reportById");
        ReportDTO reportDTO = (ReportDTO) request.getAttribute("reportDTO");

        initialReport.setIncome(reportDTO.getIncome());
        initialReport.setTaxRate(reportDTO.getTaxRate());
        initialReport.setStatus(String.valueOf(ReportStatus.PROCESSING));
        initialReport.setTaxPeriod(reportDTO.getTaxPeriod());
        initialReport.setYear(reportDTO.getYear());

        reportService.updateEditedReport(initialReport);

        session.removeAttribute("reportById");

        return "redirect:/user/reports";
    }
}
