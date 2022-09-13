package com.my.web.controller.command.app;

import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return "error/error";
    }
}

