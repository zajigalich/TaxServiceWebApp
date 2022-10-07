package com.my.web.controller.command.inspector;

import com.my.service.InspectorService;
import com.my.web.controller.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InspectorStatisticCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        ServletContext servletContext = request.getServletContext();
        InspectorService inspectorService = (InspectorService) servletContext.getAttribute("inspectorService");


        request.setAttribute("statisticData", inspectorService.getStatisticData());

        return "/WEB-INF/inspector/statistic";
    }
}
