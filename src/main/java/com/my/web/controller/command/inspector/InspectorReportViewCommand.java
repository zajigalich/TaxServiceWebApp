package com.my.web.controller.command.inspector;

import com.my.exception.ReportStatusException;
import com.my.exception.ReportsNotFoundException;
import com.my.persistence.entity.Report;
import com.my.service.InspectorService;
import com.my.service.ReportService;
import com.my.web.controller.command.Command;
import com.my.web.dto.ReportDTO;

import javax.servlet.http.HttpServletRequest;

public class InspectorReportViewCommand implements Command {
    private final ReportService reportService = ReportService.getInstance();
    private final InspectorService inspectorService = InspectorService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equals("GET")) {
            return processGetMethod(request);
        } else if (request.getMethod().equals("POST")) {
            return processPostMethod(request);
        }
        return null;
    }

    private String processPostMethod(HttpServletRequest request) {

        String status = request.getParameter("status");
        String comment = request.getParameter("comment");
        ReportDTO report = (ReportDTO) request.getSession().getAttribute("report");

        try {
            inspectorService.setReportStatus(report, status, comment);
        } catch (ReportStatusException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("comment", comment);
            return "/WEB-INF/inspector/report-view";
        }
        return "redirect:/inspector/reports";
    }

    public String processGetMethod(HttpServletRequest request) {

        try {
            Long reportId = Long.parseLong(request.getParameter("reportId"));
            ReportDTO report = reportService.getReportById(reportId);
            request.getSession().setAttribute("report", report);
        } catch (NumberFormatException e) {
            request.setAttribute("errorInvalidParam", "Invalid parameter");
        } catch (ReportsNotFoundException e) {
            request.setAttribute("noReportsFoundException", e.getMessage());
        }
        return "/WEB-INF/inspector/report-view";
    }

    @Override
    public String toString() {
        return "InspectorReportViewCommand";
    }
}
