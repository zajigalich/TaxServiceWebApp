package com.my.web.controller.command.inspector;

import com.my.service.InspectorService;
import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InspectorStatisticCommand implements Command {

    private final InspectorService inspectorService = InspectorService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("statisticData", inspectorService.getStatisticData());
        //request.getSession().setAttribute("statisticData", inspectorService.getStatisticData());

        return "/WEB-INF/inspector/statistic";
    }
}
