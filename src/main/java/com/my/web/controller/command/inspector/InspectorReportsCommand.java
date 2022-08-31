package com.my.web.controller.command.inspector;


import com.my.exception.ReportsNotFoundException;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.service.InspectorService;
import com.my.web.controller.command.Command;
import com.my.web.dto.SortField;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class  InspectorReportsCommand implements Command {

    private final InspectorService inspectorService = InspectorService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equals("GET")) {
            return reportsGet(request);
        }

        return null;
    }

    public String reportsGet(HttpServletRequest request) {

        Long id = (Long) request.getAttribute("userId");
        Date date = (Date) request.getAttribute("date");
        TaxPeriod period = (TaxPeriod) request.getAttribute("period");
        ReportStatus status = (ReportStatus) request.getAttribute("status");
        SortField sortBy = (SortField) request.getAttribute("sortBy");

        try {
            request.setAttribute("reports", inspectorService
                    .getReportsByFilterParam(id, date, period, status, sortBy));
        } catch (ReportsNotFoundException e) {
            request.setAttribute("noReportsFound", e.getMessage());
        }

        return "/WEB-INF/inspector/reports";
    }

    @Override
    public String toString() {
        return "InspectorReportsCommand";
    }
}
