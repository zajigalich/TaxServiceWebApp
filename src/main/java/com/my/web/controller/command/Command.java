package com.my.web.controller.command;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLException;

public interface Command {
    String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException;

}
