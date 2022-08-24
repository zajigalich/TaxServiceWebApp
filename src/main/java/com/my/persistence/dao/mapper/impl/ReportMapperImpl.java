package com.my.persistence.dao.mapper.impl;

import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.my.persistence.dao.FieldDB.*;

public class ReportMapperImpl implements ObjectMapper<Report> {
    @Override
    public Report extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Report.builder()
                .id(resultSet.getLong(ENTITY_ID.name()))
                .taxPeriod(TaxPeriod.valueOf(resultSet.getString(REPORT_PERIOD_ID.name())))
                .year(resultSet.getInt(REPORT_YEAR.name()))
                .income(resultSet.getInt(REPORT_INCOME.name()))
                .taxRate(resultSet.getInt(REPORT_TAX_RATE.name()))
                .reportDate(resultSet.getDate(REPORT_DATE.name()))
                .lastChangeDate(resultSet.getDate(REPORT_LAST_CHANGE_DATE.name()))
                .status(ReportStatus.valueOf(resultSet.getString(REPORT_STATUS_ID.name())))
                .comment(resultSet.getString(REPORT_COMMENT.name()))
                .userId(resultSet.getLong(REPORT_USER_ID.name()))
                .build();
    }
}