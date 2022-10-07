package com.my.web.controller.command.user;

import com.google.gson.Gson;
import com.my.service.ReportService;
import com.my.web.controller.command.Command;
import com.my.web.dto.ReportDTO;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserReportSaveJsonCommand implements Command {

    private static final Logger log = Logger.getLogger(UserReportSaveJsonCommand.class);

    private final Gson gson = new Gson();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ServletContext servletContext = request.getServletContext();
        ReportService reportService = (ReportService) servletContext.getAttribute("reportService");


        ReportDTO report = reportService.getReportById(Long.parseLong(request.getParameter("reportId")));
        report.getUserDTO().setPassword("********");
        String reportJsonString = this.gson.toJson(report);


        response.setContentType("application/json"); // Tell browser what content type the response body represents, so that it can associate it with e.g. MS Excel, if necessary.
        response.setCharacterEncoding("cp1251");
        response.setHeader("Content-Disposition", "attachment; filename=report_" + report.getId() + ".json"); // Force "Save As" dialogue.
        try (PrintWriter writer = response.getWriter()) {
            writer.write(reportJsonString); // Write CSV file to response. This will be saved in the location specified by the user.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/user/reports";
    }


    @Override
    public String toString() {
        return "UserReportSaveJsonCommand";
    }
}
