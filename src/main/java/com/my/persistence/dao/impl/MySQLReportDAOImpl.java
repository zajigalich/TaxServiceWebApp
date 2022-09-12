package com.my.persistence.dao.impl;

import com.my.persistence.dao.mapper.impl.UserMapperImpl;
import com.my.persistence.db.ManagerDB;
import com.my.persistence.dao.ReportDAO;
import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.dao.mapper.impl.ReportMapperImpl;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.web.dto.SortField;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MySQLReportDAOImpl implements ReportDAO {

    private final static Logger log = Logger.getLogger(MySQLReportDAOImpl.class);

    private final static ObjectMapper<Report> mapper = new ReportMapperImpl();

    private final static String SAVE_REPORT = "INSERT INTO "
            + "report (comment, income, status_id, period_id, tax_rate, year, user_id) "
            + "values (?, ?, ?, ?, ?, ?, ?);";

    private final static String UPDATE_REPORT = "UPDATE report r SET r.comment = ?, r.income = ?, r.status_id = ?," +
            "r.report_date = ?, r.period_id = ?, r.tax_rate = ?, r.year = ?, r.user_id = ? "
            + " WHERE r.id = ?";

    private final static String FIND_BY_PARAM = "SELECT r.* FROM report r " +
            "WHERE r.user_id = (IF(? IS NULL, r.user_id, ?))" +
            "AND r.report_date = (IF(? IS NULL, r.report_date, ?))" +
            "AND r.period_id = (IF(? IS NULL, r.period_id, ?))" +
            "AND r.status_id = (IF(? IS NULL, r.status_id, ?))" +
            "ORDER BY ";

    private final static String DELETE_REPORT = "DELETE FROM report r WHERE r.id = ?";

    private final static String FIND_BY_PARAM_WITH_USER = "SELECT rr.*, u.* "
            + "FROM ( SELECT r.* FROM report r "
            + "WHERE r.user_id = (IF(? IS NULL, r.user_id, ?))"
            + "AND r.report_date = (IF(? IS NULL, r.report_date, ?))"
            + "AND r.period_id = (IF(? IS NULL, r.period_id, ?))"
            + "AND r.status_id = (IF(? IS NULL, r.status_id, ?))"
            + ") rr LEFT JOIN user u ON rr.user_id = u.id ORDER BY ";

    private final static String FIND_REPORT_BY_ID = "SELECT * FROM report WHERE id = ?";

    private final static String STATISTIC_REPORTS_COUNT = "SELECT COUNT(*) AS count, " +
            "SUM(IF(r.status_id = '1', 1, 0))  AS processing_count, " +
            "SUM(IF(r.status_id = '2', 1, 0))    AS approved_count,       " +
            "SUM(IF(r.status_id = '3', 1, 0)) AS disapproved_count " +
            "FROM report r;";

    private static final String STATISTIC_REPORTS_COUNT_PER_YEAR = "SELECT r.year, COUNT(*) as count " +
            "FROM report as r group by r.year order by r.year;";

    private static final String FIND_ALL_REPORTS = "SELECT * FROM report";

    MySQLReportDAOImpl() {
    }

    @Override
    public Report create(Report report) {
        log.info("creating report");
        log.info(report.toString());
        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_REPORT)) {
                int k = 0;
                preparedStatement.setString(++k, report.getComment());
                preparedStatement.setInt(++k, report.getIncome());
                preparedStatement.setInt(++k, report.getStatus().ordinal() + 1);
                // preparedStatement.setDate(++k, report.getReportDate());
                preparedStatement.setInt(++k, report.getTaxPeriod().ordinal() + 1);
                preparedStatement.setInt(++k, report.getTaxRate());
                preparedStatement.setInt(++k, report.getYear());
                preparedStatement.setLong(++k, report.getUserId());

                log.info(preparedStatement.toString());
                preparedStatement.execute();
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return report;
    }

    @Override
    public Report update(Report report) {
        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REPORT)) {
                int k = 0;
                preparedStatement.setString(++k, report.getComment());
                preparedStatement.setInt(++k, report.getIncome());
                preparedStatement.setInt(++k, report.getStatus().ordinal() + 1);
                preparedStatement.setDate(++k, report.getReportDate());
                preparedStatement.setInt(++k, report.getTaxPeriod().ordinal() + 1);
                preparedStatement.setInt(++k, report.getTaxRate());
                preparedStatement.setInt(++k, report.getYear());
                preparedStatement.setLong(++k, report.getUserId());
                preparedStatement.setLong(++k, report.getId());

                preparedStatement.execute();
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return report;
    }

    @Override
    public boolean delete(Long id) {
        log.info(id);

        boolean isExecuted = false;

        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REPORT)) {
                preparedStatement.setLong(1, id);
                isExecuted = preparedStatement.execute();
                connection.commit();
                preparedStatement.close();
                log.info(isExecuted);
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return isExecuted;
    }

    @Override
    public Optional<Report> findById(Long id) {

        Report report = null;
        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_REPORT_BY_ID)) {

                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    report = mapper.extractFromResultSet(resultSet);
                }
                resultSet.close();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(report);
    }

    @Override
    public List<Report> findAll() {

        List<Report> reports = null;
        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_REPORTS)) {

                ResultSet resultSet = statement.executeQuery();

                reports = new ArrayList<>();
                while (resultSet.next()) {
                    Report report = mapper.extractFromResultSet(resultSet);
                    report.setUser(UserMapperImpl.extractUserFromResultSetForReport(resultSet));
                    reports.add(report);

                }
                resultSet.close();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    @Override
    public List<Report> findByParamWithUser(Long id, Date reportDate, TaxPeriod period, ReportStatus status, SortField sortField) {

        log.info("findByParamWithUser work");

        String sortBy = sortField == null ? "r.id " : sortField.fieldInTable + " " + sortField.direction;

        String query = FIND_BY_PARAM_WITH_USER + sortBy;

        List<Report> reports = null;
        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setObject(1, id, Types.BIGINT);
                statement.setObject(2, id, Types.BIGINT);

                if (reportDate == null){
                    statement.setNull(3, Types.DATE);
                    statement.setNull(4, Types.DATE);
                } else {
                    statement.setDate(3, reportDate);
                    statement.setDate(4, reportDate);
                }

                if (period == null) {
                    statement.setNull(5, java.sql.Types.INTEGER);
                    statement.setNull(6, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(5, period.ordinal() + 1);
                    statement.setInt(6, period.ordinal() + 1);
                }

                if (status == null) {
                    statement.setNull(7, java.sql.Types.INTEGER);
                    statement.setNull(8, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(7, status.ordinal() + 1);
                    statement.setInt(8, status.ordinal() + 1);
                }


                log.info(statement.toString());
                ResultSet resultSet = statement.executeQuery();

                reports = new ArrayList<>();
                while (resultSet.next()) {
                    Report report = mapper.extractFromResultSet(resultSet);
                    report.setUser(UserMapperImpl.extractUserFromResultSetForReport(resultSet));
                    reports.add(report);

                }
                resultSet.close();
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }

    @Override
    public List<Report> findByParam(Long id, Date reportDate, TaxPeriod period, ReportStatus status, SortField
            sortField) {
        String sortBy = sortField == null ? "r.id " : sortField.fieldInTable + " " + sortField.direction;

        String query = FIND_BY_PARAM + sortBy;

        List<Report> reports = null;
        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setObject(1, id, Types.BIGINT);
                statement.setObject(2, id, Types.BIGINT);

                if (reportDate == null){
                    statement.setNull(3, Types.DATE);
                    statement.setNull(4, Types.DATE);
                } else {
                    statement.setDate(3, reportDate);
                    statement.setDate(4, reportDate);
                }

                if (period == null) {
                    statement.setNull(5, java.sql.Types.INTEGER);
                    statement.setNull(6, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(5, period.ordinal() + 1);
                    statement.setInt(6, period.ordinal() + 1);
                }

                if (status == null) {
                    statement.setNull(7, java.sql.Types.INTEGER);
                    statement.setNull(8, java.sql.Types.INTEGER);
                } else {
                    statement.setInt(7, status.ordinal() + 1);
                    statement.setInt(8, status.ordinal() + 1);
                }

                ResultSet resultSet = statement.executeQuery();
                reports = new ArrayList<>();

                while (resultSet.next()) {
                    Report report = mapper.extractFromResultSet(resultSet);
                    log.info(report.toString());
                    reports.add(report);

                }
                resultSet.close();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    @Override
    public Map<String, Long> getStatisticDataReportsCount() {

        Map<String, Long> data = new TreeMap<>();

        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(STATISTIC_REPORTS_COUNT)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    data.put("count", resultSet.getLong("count"));
                    data.put("processing_count", resultSet.getLong("processing_count"));
                    data.put("approved_count", resultSet.getLong("approved_count"));
                    data.put("disapproved_count", resultSet.getLong("disapproved_count"));
                }
                resultSet.close();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Map<Integer, Long> getStatisticDataReportsPerYearCount() {

        Map<Integer, Long> data = new TreeMap<>();

        try (Connection connection = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(STATISTIC_REPORTS_COUNT_PER_YEAR)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    data.put(resultSet.getInt("year"), resultSet.getLong("count"));
                }
                resultSet.close();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
