package com.my.web.controller.command.inspector;

import com.my.service.InspectorService;
import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class InspectorStatisticCommand implements Command {

    private final InspectorService inspectorService = InspectorService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {

        request.setAttribute("statisticData", inspectorService.getStatisticData());
        //request.getSession().setAttribute("statisticData", inspectorService.getStatisticData());

        return "/WEB-INF/inspector/statistic";
    }
}
