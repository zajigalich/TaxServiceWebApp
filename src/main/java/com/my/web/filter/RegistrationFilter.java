package com.my.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

@WebFilter(urlPatterns = "/registration")
public class RegistrationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        AtomicBoolean isParamCorrect = new AtomicBoolean(true);

        servletRequest.getParameterMap().forEach((k, v) -> {
            if (v == null || v[0].equals("")) {
                isParamCorrect.set(false);
            }
        });

        if (isParamCorrect.get())
            filterChain.doFilter(servletRequest, servletResponse);
        else {
            servletRequest.getParameterMap().forEach(servletRequest::setAttribute);
            servletRequest.getRequestDispatcher("/registration.jsp").forward(servletRequest, servletResponse);
        }
    }
}