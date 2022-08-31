package com.my.service;

import com.my.exception.ReportStatusException;
import com.my.exception.ReportsNotFoundException;
import com.my.persistence.dao.ReportDAO;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.impl.DAOFactory;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.web.dto.ReportDTO;
import com.my.web.dto.SortField;
import com.my.web.dto.StatisticDTO;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InspectorService {

    private final ReportDAO reportDao = DAOFactory.getReportDaoInstance();
    private final UserDAO userDao = DAOFactory.getUserDaoInstance();

    private static InspectorService inspectorService;

    public static synchronized InspectorService getInstance() {
        if (inspectorService == null)
            inspectorService = new InspectorService();

        return inspectorService;
    }

    public List<ReportDTO> getReportsByFilterParam(Long id, Date reportDate, TaxPeriod period,
                                                   ReportStatus status, SortField sortField) {

        List<Report> reportList;

        reportList = reportDao.findByParamWithUser(id, reportDate, period, status, sortField);

        if (reportList == null || reportList.isEmpty())
            throw new ReportsNotFoundException("No reports found");

        return reportList.stream()
                .map(EntityDTOUtil::convertReportEntityToDTO)
                .collect(Collectors.toList());
    }

    public StatisticDTO getStatisticData() {

        Map<String, Long> statisticDataReportsCount = reportDao.getStatisticDataReportsCount();

        Map<Integer, Long> statisticDataReportsPerYearCount = reportDao.getStatisticDataReportsPerYearCount();

        Map<String, Long> statisticDataUsersCountByRoles = userDao.getStatisticDataUsersCountByRoles();

        return StatisticDTO.builder()
                .countOfReports(statisticDataReportsCount.get("count"))
                .processingReports(statisticDataReportsCount.get("processing_count"))
                .approvedReports(statisticDataReportsCount.get("approved_count"))
                .disapprovedReports(statisticDataReportsCount.get("disapproved_count"))
                .countReportsPerYear(statisticDataReportsPerYearCount)
                .countOfUsers(statisticDataUsersCountByRoles.get("user_count"))
                .countOfInspectors(statisticDataUsersCountByRoles.get("inspector_count"))
                .build();
    }

    public Report setReportStatus(ReportDTO report, String status, String comment) {

        if (status == null || status.isEmpty()) {
            throw new ReportStatusException("Require status");
        }

        if (ReportStatus.valueOf(status).equals(ReportStatus.DISAPPROVED) && comment.isEmpty()) {
            throw new ReportStatusException("Require comment");
        }

        report.setComment(comment.trim());
        report.setStatus(status);

        return reportDao.update(EntityDTOUtil.convertReportDTOToEntity(report));
    }
}
