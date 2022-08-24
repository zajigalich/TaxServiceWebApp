package com.my.persistence.dao;

import com.my.persistence.entity.Report;
import com.my.persistence.entity.Status;
import com.my.persistence.entity.TaxPeriod;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ReportDAO extends BaseDAO<Report> {

    List<Report> findByParamWithUser(Long id, Date reportDate, TaxPeriod period,
                                     Status status); //SortField sortField;


    List<Report> findByParam(Long id, Date reportDate, TaxPeriod period,
                             Status status); //SortField sortField;

    Map<String, Long> getStatisticDataReportsCount();

    Map<Integer, Long> getStatisticDataReportsPerYearCount();
}
