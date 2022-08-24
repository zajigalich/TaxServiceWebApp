package com.my.persistence.dao;

import com.my.persistence.dao.impl.ReportDAOImpl;
import com.my.persistence.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static ReportDAO reportDAO;
    private static UserDAO userDAO;

    public static ReportDAO getReportDaoInstance() {
        if (reportDAO == null)
            reportDAO = new ReportDAOImpl();

        return reportDAO;
    }

    public static UserDAO getUserDaoInstance() {
        if (userDAO == null)
            userDAO = new UserDAOImpl();

        return userDAO;
    }
}
