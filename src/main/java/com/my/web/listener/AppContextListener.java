package com.my.web.listener;

import com.my.service.InspectorService;
import com.my.service.RegistrationService;
import com.my.service.ReportService;
import com.my.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext context = servletContextEvent.getServletContext();
        UserService userService = new UserService();
        RegistrationService registrationService = new RegistrationService();
        ReportService reportService = new ReportService();
        InspectorService inspectorService = new InspectorService();

        context.setAttribute("userService", userService);
        context.setAttribute("registrationService", registrationService);
        context.setAttribute("reportService", reportService);
        context.setAttribute("inspectorService", inspectorService);
    }
}
