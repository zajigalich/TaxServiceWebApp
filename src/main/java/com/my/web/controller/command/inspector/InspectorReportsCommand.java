package com.my.web.controller.command.inspector;


import com.my.exception.ReportsNotFoundException;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.service.InspectorService;
import com.my.web.controller.command.Command;
import com.my.web.dto.SortField;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class InspectorReportsCommand implements Command {

    private static final Logger log = Logger.getLogger(InspectorReportsCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ServletContext servletContext = request.getServletContext();
        InspectorService inspectorService = (InspectorService) servletContext.getAttribute("inspectorService");

        log.debug(request.getRequestURI());
        log.info("Request attributes: " + request.getAttribute("userId") + ", "
                + ", " + request.getAttribute("date") + ", " + request.getAttribute("period") + ", "
                + request.getAttribute("status") + ", " + request.getAttribute("sortBy"));

        Long id = (Long) request.getAttribute("userId");
        //Long id = null;
        Date date = (Date) request.getAttribute("date");
        TaxPeriod period = (TaxPeriod) request.getAttribute("period");
        ReportStatus status = (ReportStatus) request.getAttribute("status");
        SortField sortBy = (SortField) request.getAttribute("sortBy");

        //log.info(""+ id + date + period + status);
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
