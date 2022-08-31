package com.my.web.controller.command.user;

import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UserInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        return "/WEB-INF/user/user-info";
    }
}