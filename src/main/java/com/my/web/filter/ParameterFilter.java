package com.my.web.filter;

import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.web.dto.SortField;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebFilter(value = {"/user/reports", "/inspector/reports"})
public class ParameterFilter implements Filter {

    private static final Logger log = Logger.getLogger(ParameterFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        String dateParam = request.getParameter("date");
        String periodParam = request.getParameter("period");
        String statusParam = request.getParameter("status");
        String sortByParam = request.getParameter("sortBy");

        if (dateParam != null) {
            session.setAttribute("date", dateParam.isEmpty() ? null : Date.valueOf(dateParam));
        }

        if (periodParam != null) {
            session.setAttribute("period", periodParam.isEmpty() ? null : TaxPeriod.valueOf(periodParam));
        }

        if (statusParam != null) {
            session.setAttribute("status", statusParam.isEmpty() ? null : ReportStatus.valueOf(statusParam));
        }

        if (sortByParam != null) {
            session.setAttribute("sortBy", sortByParam.isEmpty() ? null : SortField.valueOf(sortByParam));
        }

        request.setAttribute("date", session.getAttribute("date"));
        request.setAttribute("period", session.getAttribute("period"));
        request.setAttribute("status", session.getAttribute("status"));
        request.setAttribute("sortBy", session.getAttribute("sortBy"));



        filterChain.doFilter(servletRequest, servletResponse);
    }
}
