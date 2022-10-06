package com.my.persistence.dao.impl;

import com.my.persistence.dao.ReportDAO;
import com.my.persistence.dao.UserDAO;

public class DAOFactory {

    private static ReportDAO reportDAO;
    private static UserDAO userDAO;

    public static ReportDAO getReportDaoInstance() {
        if (reportDAO == null)
            reportDAO = new MySQLReportDAOImpl();

        return reportDAO;
    }

    public static UserDAO getUserDaoInstance() {
        if (userDAO == null)
            userDAO = new MySQLUserDAOImpl();

        return userDAO;
    }
}