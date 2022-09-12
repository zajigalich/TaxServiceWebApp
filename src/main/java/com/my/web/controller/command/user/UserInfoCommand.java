package com.my.web.controller.command.user;

import com.my.persistence.entity.User;
import com.my.web.EntityDTOUtil;
import com.my.web.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class UserInfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("userDTO", EntityDTOUtil.convertUserEntityToDto(((User) request.getSession().getAttribute("user"))));
        return "/WEB-INF/user/user-info";
    }
}