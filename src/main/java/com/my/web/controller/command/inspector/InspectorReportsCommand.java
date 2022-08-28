package com.my.web.controller.command.inspector;


import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class  InspectorReportsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getMethod().equalsIgnoreCase("get")){
            return "/WEB-INF/inspector/reports";
        }

       // HttpSession session = request.getSession();
        return "/";
    }

    @Override
    public String toString() {
        return "InspectorReportsCommand";
    }
}
