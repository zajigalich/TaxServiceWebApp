package com.my.web.controller.command.app;

import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
