package com.my.web.filter;

import com.my.persistence.entity.User;
import com.my.persistence.entity.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/inspector/*"})
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String requestURI = request.getRequestURI();

        if (user != null) {
            if (user.getUserRole().equals(UserRole.USER) && requestURI.contains("/user/")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (user.getUserRole().equals(UserRole.INSPECTOR) && requestURI.contains("/inspector/")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.getRequestDispatcher("/error/error.jsp").forward(servletRequest, servletResponse);
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}