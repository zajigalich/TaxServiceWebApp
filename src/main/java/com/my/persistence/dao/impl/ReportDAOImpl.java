package com.my.persistence.dao.impl;

import com.my.persistence.dao.ReportDAO;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.Status;
import com.my.persistence.entity.TaxPeriod;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ReportDAOImpl implements ReportDAO {
    @Override
    public Report create(Report entity) {
            return null;
    }

    @Override
    public Report update(Report entity) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Report findById(String id) {
        return null;
    }

    @Override
    public Collection<Report> findAll() {
        return null;
    }

    @Override
    public List<Report> findByParamWithUser(Long id, Date reportDate, TaxPeriod period, Status status) {
        return null;
    }

    @Override
    public List<Report> findByParam(Long id, Date reportDate, TaxPeriod period, Status status) {
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
