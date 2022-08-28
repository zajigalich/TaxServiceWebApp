package com.my.web.filter;

import com.my.web.controller.FrontControllerServlet;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/login")
public class LoginFilter implements Filter {

    private static final Logger log = Logger.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //log.debug("Login filter works");
        if (email == null || password == null) {
            //log.debug("Login redirect");
            servletRequest.getRequestDispatcher("/login.jsp").forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
