package com.my.persistence.dao.impl;

import com.my.persistence.dao.ManagerDB;
import com.my.persistence.dao.ReportDAO;
import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.dao.mapper.impl.ReportMapperImpl;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReportDAOImpl implements ReportDAO {

    private final static String SAVE_REPORT = "INSERT INTO " +
            "report (comment, income, status_id, report_date, period_id, tax_rate, year, user_id) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String UPDATE_REPORT = "UPDATE report r SET r.comment = ?, r.income = ?, r.status = ?, " +
            " r.report_date = ?, r.tax_period = ?, r.tax_rate = ?, r.year = ?, r.user_id = ? " +
            " WHERE r.id = ?";

    ObjectMapper<Report> mapper = new ReportMapperImpl();

    ReportDAOImpl() {
    }

    @Override
    public Report create(Report report) {
        return setValuesCreateUpdate(report, SAVE_REPORT);
    }

    @Override
    public Report update(Report report) {
        return setValuesCreateUpdate(report, UPDATE_REPORT);
    }

    private Report setValuesCreateUpdate(Report report, String updateReport) {
        try (Connection connection = ManagerDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateReport)) {
            int k = 0;
            preparedStatement.setString(++k, report.getComment());
            preparedStatement.setInt(++k, report.getIncome());
            preparedStatement.setInt(++k, report.getStatus().ordinal());
            preparedStatement.setDate(++k, report.getReportDate());
            preparedStatement.setInt(++k, report.getTaxPeriod().ordinal());
            preparedStatement.setInt(++k, report.getTaxRate());
            preparedStatement.setInt(++k, report.getYear());
            preparedStatement.setLong(++k, report.getUserId());

            preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return report;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Optional<Report> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Collection<Report> findAll() {
        return null;
    }

    @Override
    public List<Report> findByParamWithUser(Long id, Date reportDate, TaxPeriod period, ReportStatus status) {
        return null;
    }

    @Override
    public List<Report> findByParam(Long id, Date reportDate, TaxPeriod period, ReportStatus status) {
        return null;
    }

    @Override
    public Map<String, Long> getStatisticDataReportsCount() {
        return null;
    }

    @Override
    public Map<Integer, Long> getStatisticDataReportsPerYearCount() {
        return null;
    }
}
