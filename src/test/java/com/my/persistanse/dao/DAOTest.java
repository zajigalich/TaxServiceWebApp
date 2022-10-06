package com.my.persistanse.dao;

import com.my.exception.UserAlreadyExistsException;
import com.my.persistence.dao.ReportDAO;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.impl.DAOFactory;
import com.my.persistence.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class DAOTest {

    @Test
    public void userDAOTest() {

        UserDAO userDAO = DAOFactory.getUserDaoInstance();

        User testUser = User.builder()
                .name("TestUser")
                .lastName("TestUser")
                .userRole(UserRole.USER)
                .entrepreneurType(EntrepreneurType.PHYSICAL_PERSON)
                .email("t@t.t")
                .password("123456")
                .address("test")
                .tin("00000000")
                .build();

        Assert.assertTrue(userDAO.create(testUser));

        Assert.assertThrows(UserAlreadyExistsException.class, () -> {
            userDAO.create(testUser);
        });

        Assert.assertTrue(userDAO.findByEmail(testUser.getEmail()).isPresent());

        User actual1 = userDAO.findByEmail(testUser.getEmail()).get();

        User actual2 = userDAO.findById(actual1.getId()).get();

        Assert.assertEquals(actual1, actual2);

        Assert.assertEquals(actual1.getEmail(), testUser.getEmail());
        Assert.assertEquals(actual1.getName(), testUser.getName());
        Assert.assertEquals(actual1.getLastName(), testUser.getLastName());
        Assert.assertEquals(actual1.getUserRole(), testUser.getUserRole());
        Assert.assertEquals(actual1.getPassword(), testUser.getPassword());
        Assert.assertEquals(actual1.getEntrepreneurType(), testUser.getEntrepreneurType());
        Assert.assertEquals(actual1.getTin(), testUser.getTin());
        Assert.assertEquals(actual1.getDateOfRegistration().toString(), new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                .format(Calendar.getInstance().getTime()));


        Assert.assertTrue(userDAO.delete(actual1.getId()));
    }

    @Test
    public void reportDAOTest() {

        UserDAO userDAO = DAOFactory.getUserDaoInstance();
        ReportDAO reportDAO = DAOFactory.getReportDaoInstance();

        User testUser = User.builder()
                .name("TestUser")
                .lastName("TestUser")
                .userRole(UserRole.USER)
                .entrepreneurType(EntrepreneurType.PHYSICAL_PERSON)
                .email("t@t.t")
                .password("123456")
                .address("test")
                .tin("00000000")
                .build();

        userDAO.create(testUser);

        Long testUserId = userDAO.findByEmail(testUser.getEmail()).get().getId();

        testUser.setId(testUserId);

        System.out.println(testUser);

        Report testReport = Report.builder()
                .comment("comment")
                .user(testUser)
                .income(1)
                .status(ReportStatus.APPROVED)
                .taxRate(1)
                .year(2001)
                .taxPeriod(TaxPeriod.FIRST_PERIOD)
                .user(testUser)
                .userId(testUserId)
                .build();

        reportDAO.create(testReport);

        List<Report> reports = reportDAO.findByParamWithUser(null,
                new java.sql.Date(new java.util.Date().getTime()),
                TaxPeriod.FIRST_PERIOD,
                ReportStatus.APPROVED,
                null);

        Report actual = reports.get(0);

        Assert.assertEquals(actual.getUserId(), testReport.getUserId());
        Assert.assertEquals(actual.getComment(), testReport.getComment());
        //Assert.assertEquals(actual.getUser(), testReport.getUser());
        Assert.assertEquals(actual.getIncome(), testReport.getIncome());
        Assert.assertEquals(actual.getStatus(), testReport.getStatus());
        Assert.assertEquals(actual.getTaxRate(), testReport.getTaxRate());
        Assert.assertEquals(actual.getTaxPeriod(), testReport.getTaxPeriod());
        Assert.assertEquals(actual.getYear(), testReport.getYear());
        //Assert.assertEquals(actual, testReport);


        reports = reportDAO.findByParam(null,
                new java.sql.Date(new java.util.Date().getTime()),
                TaxPeriod.FIRST_PERIOD,
                ReportStatus.APPROVED,
                null);

        actual = reports.get(0);

        Assert.assertEquals(actual.getUserId(), testReport.getUserId());
        Assert.assertEquals(actual.getComment(), testReport.getComment());
        Assert.assertEquals(actual.getIncome(), testReport.getIncome());
        Assert.assertEquals(actual.getStatus(), testReport.getStatus());
        Assert.assertEquals(actual.getTaxRate(), testReport.getTaxRate());
        Assert.assertEquals(actual.getTaxPeriod(), testReport.getTaxPeriod());
        Assert.assertEquals(actual.getYear(), testReport.getYear());

        userDAO.delete(testUserId);
        reportDAO.delete(actual.getId());

    }
}