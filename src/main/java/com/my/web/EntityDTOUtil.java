package com.my.web;

import com.my.persistence.entity.Report;
import com.my.persistence.entity.ReportStatus;
import com.my.persistence.entity.TaxPeriod;
import com.my.persistence.entity.User;
import com.my.web.dto.ReportDTO;
import com.my.web.dto.UserDTO;
import org.apache.log4j.Logger;

public class EntityDTOUtil {

    private static final Logger log = Logger.getLogger(EntityDTOUtil.class);

    private EntityDTOUtil() {}

    public static UserDTO convertUserEntityToDto(User user) {
        return UserDTO.builder()
                .firstName(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .tin(user.getTin())
                .dateOfRegistration(user.getDateOfRegistration())
                .address(user.getAddress())
                .entrepreneurType(String.valueOf(user.getEntrepreneurType()))
                .userRole(String.valueOf(user.getUserRole()))
                .userId(user.getId())
                .build();
    }

    public static ReportDTO convertReportEntityToDTO(Report report) {
        return new ReportDTO.Builder()
                .id(report.getId())
                .income(report.getIncome())
                .taxRate(report.getTaxRate())
                .taxPeriod(report.getTaxPeriod())
                .year(report.getYear())
                .status(report.getStatus())
                .reportDate(report.getReportDate())
                .comment(report.getComment())
                .userId(report.getUserId())
                .build();
    }

    public static ReportDTO convertReportEntityToDTO(Report report, User user) {
        return new ReportDTO.Builder()
                .id(report.getId())
                .income(report.getIncome())
                .taxRate(report.getTaxRate())
                .taxPeriod(report.getTaxPeriod())
                .year(report.getYear())
                .status(report.getStatus())
                .reportDate(report.getReportDate())
                .comment(report.getComment())
                .userId(report.getUserId())
                .user(convertUserEntityToDto(user))
                .build();
    }

    public static Report convertReportDTOToEntity(ReportDTO report) {
        return new Report.Builder()
                .id(report.getId())
                .income(report.getIncome())
                .taxRate(report.getTaxRate())
                .taxPeriod(TaxPeriod.valueOf(report.getTaxPeriod()))
                .year(report.getYear())
                .status(ReportStatus.valueOf(report.getStatus()))
                .reportDate(report.getReportDate())
                .comment(report.getComment())
                .userId(report.getUserId())
                .build();
    }
}
