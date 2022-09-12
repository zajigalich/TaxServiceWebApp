package com.my.web.controller.command.user;

import com.my.exception.ReportsNotFoundException;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.persistence.entity.User;
import com.my.service.ReportService;
import com.my.web.dto.SortField;
import com.my.web.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class UserReportsCommand implements Command {

    private final ReportService reportService = ReportService.getInstance();

    private static final Logger log = Logger.getLogger(UserReportsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        log.debug(request.getRequestURI() + "  " + request.getMethod());

        Long id = ((User) request.getSession().getAttribute("user")).getId();
        Date date = (Date) request.getAttribute("date");
        TaxPeriod period = (TaxPeriod) request.getAttribute("period");
        ReportStatus status = (ReportStatus) request.getAttribute("status");
        SortField sortBy = (SortField) request.getAttribute("sortBy");

        log.info("user rep params  " + id + ", " + date + ", " + period + ", " + status + ", " + sortBy + ", ");
        try {
            request.setAttribute("reports",
                    reportService.getReportsByFilterParam(id, date, period, status, sortBy));
        } catch (ReportsNotFoundException e) {
            request.setAttribute("noReportsFoundException", e.getMessage());
        }


        return "/WEB-INF/user/reports";
    }

    @Override
    public String toString() {
        return "UserReportsCommand";
    }
}
