package com.my.web.filter;

import com.my.persistence.entity.TaxPeriod;
import com.my.web.controller.command.user.UserReportApplyCommand;
import com.my.web.dto.ReportDTO;
import com.my.web.dto.ReportFormError;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

@WebFilter(value = {"/user/report-form", "/user/report-edit"})
public class ReportFormFilter implements Filter {

    private static final Logger log = Logger.getLogger(ReportFormFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getMethod().equalsIgnoreCase("POST")) {

            String income = request.getParameter("income");
            String taxRate = request.getParameter("taxRate");
            String period = request.getParameter("period");
            String year = request.getParameter("year");


            log.info("report: " + income + ", " + taxRate + ", "+ period + ", "+ year);

            ReportFormError reportFormError = new ReportFormError();

            if (income.isEmpty() || !Pattern.matches("^[1-9][0-9]+$", income))
                reportFormError.setIncomeInvalid(true);

            if (taxRate.isEmpty() || !Pattern.matches("^[1-9][0-9]?$", taxRate))
                reportFormError.setTaxRateInvalid(true);

            if (period.isEmpty())
                reportFormError.setPeriodInvalid(true);

            if (year.isEmpty() || !Pattern.matches("^[2][0][0-9]{2}$", year))
                reportFormError.setYearInvalid(true);

            if (!reportFormError.hasErrors()) {
                ReportDTO reportDTO = ReportDTO.builder()
                        .income(Integer.valueOf(income))
                        .taxRate(Integer.valueOf(taxRate))
                        .taxPeriod(TaxPeriod.valueOf(period))
                        .year(Integer.valueOf(year))
                        .build();
                log.info(reportDTO);
                request.setAttribute("reportDTO", reportDTO);
            }

            request.setAttribute("errorReportFormDTO", reportFormError);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}