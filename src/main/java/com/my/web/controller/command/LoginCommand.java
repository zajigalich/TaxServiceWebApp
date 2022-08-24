package com.my.web.controller.command;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String toString() {
        return "Login";
    }
}
