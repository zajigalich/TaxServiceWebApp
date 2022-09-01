package com.my.web.controller.command.user;

import com.my.exception.ReportsNotFoundException;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.persistence.entity.User;
import com.my.service.ReportService;
import com.my.web.dto.SortField;
import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class UserReportsCommand implements Command {

    private final ReportService reportService = ReportService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getMethod().equalsIgnoreCase("get")){
            return "/WEB-INF/user/reports";
        }

        Long id = ((User) request.getSession().getAttribute("user")).getId();
        Date date = (Date) request.getAttribute("date");
        TaxPeriod period = (TaxPeriod) request.getAttribute("period");
        ReportStatus status = (ReportStatus) request.getAttribute("status");
        SortField sortBy = (SortField) request.getAttribute("sortBy");

        try {
            request.setAttribute("reports",
                    reportService.getReportsByFilterParam(id, date, period, status, sortBy));
        } catch (ReportsNotFoundException e) {
            request.setAttribute("noReportsFoundException", e.getMessage());
        }

        return "/WEB-INF/user/reports";
    }
}
