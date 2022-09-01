package com.my.service;

import com.my.exception.ReportsNotFoundException;
import com.my.persistence.dao.ReportDAO;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.impl.DAOFactory;
import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.persistence.entity.User;
import com.my.web.EntityDTOUtil;
import com.my.web.dto.ReportDTO;
import com.my.web.dto.SortField;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ReportService {

    private static final UserDAO userDAO = DAOFactory.getUserDaoInstance();
    private static final ReportDAO reportDAO = DAOFactory.getReportDaoInstance();

    private static final Logger log = Logger.getLogger(ReportService.class.getName());

    private static ReportService reportService;

    public static synchronized ReportService getInstance() {

        if (reportService == null)
            reportService = new ReportService();

        return reportService;
    }

    public List<ReportDTO> getReportsByFilterParam(Long id, Date reportDate, TaxPeriod period,
                                                ReportStatus status, SortField sortField) {

        List<Report> reportList;

        reportList = reportDAO.findByParam(id, reportDate, period, status, sortField);

        if (reportList == null || reportList.isEmpty())
            throw new ReportsNotFoundException("No reports found");

        return reportList.stream()
                .map(EntityDTOUtil::convertReportEntityToDTO)
                .collect(Collectors.toList());
    }

    public ReportDTO getReportById(Long reportId) {

        Optional<Report> report = reportDAO.findById(reportId);
        ReportDTO reportDTO = null;

        if (report.isPresent()) {
            Optional<User> user = userDAO.findById(report.get().getUserId());
            reportDTO = EntityDTOUtil.convertReportEntityToDTO(report.get(),user.get());
        }
        return reportDTO;
    }

    public boolean deleteReportById(Long reportId) {
        return reportDAO.delete(reportId);
    }

    public Report applyNewReport(ReportDTO report) {

        log.info(report.toString());
        report.setStatus(String.valueOf(ReportStatus.PROCESSING));
        report.setReportDate(Date.valueOf(LocalDate.now()));

        return reportDAO.create(EntityDTOUtil.convertReportDTOToEntity(report));
    }

    public Report updateEditedReport(ReportDTO report) {

        return reportDAO.update(EntityDTOUtil.convertReportDTOToEntity(report));
    }


}
