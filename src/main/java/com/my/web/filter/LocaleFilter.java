package com.my.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request= (HttpServletRequest) servletRequest;
        final HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpSession session =request.getSession();

        if(request.getParameter("lang")!=null){
            // Set session attribute "lang" to current on the page
            request.getSession().setAttribute("lang", request.getParameter("lang"));
        }
        if (session.getAttribute("lang") != null) {
            // Set resource bundle to current ()
            ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",
                    new Locale((String) session.getAttribute("lang")));
            session.setAttribute("resourceBundle", resourceBundle);
        } else {// Set default resource bundle EN
            ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("messages");
            session.setAttribute("resourceBundle", defaultResourceBundle);
        }
        filterChain.doFilter(request,response);
    }
}

