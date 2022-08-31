package com.my.web.controller.command.app;

import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        return "error/error";
    }
}

