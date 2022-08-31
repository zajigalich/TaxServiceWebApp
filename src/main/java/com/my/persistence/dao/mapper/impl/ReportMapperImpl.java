package com.my.persistence.dao.mapper.impl;

import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.persistence.entity.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.my.persistence.db.FieldDB.*;

public class ReportMapperImpl implements ObjectMapper<Report> {

    private static final Logger log = Logger.getLogger(ReportMapperImpl.class);

    @Override
    public Report extractFromResultSet(ResultSet resultSet) throws SQLException {
        return Report.builder()
                .id(resultSet.getLong(ENTITY_ID.getField()))
                .taxPeriod(TaxPeriod.values()[resultSet.getInt(REPORT_PERIOD_ID.getField()) - 1])
                .year(resultSet.getInt(REPORT_YEAR.getField()))
                .income(resultSet.getInt(REPORT_INCOME.getField()))
                .taxRate(resultSet.getInt(REPORT_TAX_RATE.getField()))
                .reportDate(resultSet.getDate(REPORT_DATE.getField()))
                .lastChangeDate(resultSet.getDate(REPORT_LAST_CHANGE_DATE.getField()))
                .status(ReportStatus.values()[resultSet.getInt(REPORT_STATUS_ID.getField()) - 1])
                .comment(resultSet.getString(REPORT_COMMENT.getField()))
                .userId(resultSet.getLong(REPORT_USER_ID.getField()))
                .build();
    }
}
