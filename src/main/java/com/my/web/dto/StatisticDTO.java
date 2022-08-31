package com.my.web.dto;

import java.util.Map;

public class StatisticDTO {

    private Long countOfUsers;
    private Long countOfReports;
    private Long countOfInspectors;

    private Long processingReports;
    private Long approvedReports;
    private Long disapprovedReports;

    Map<Integer, Long> countReportsPerYear;

    public static StatisticDTO.Builder builder() {
        return new StatisticDTO.Builder();
    }

    public static class Builder {

        private final StatisticDTO statisticDTO;

        public Builder() {
            statisticDTO = new StatisticDTO();
        }

        public Builder countOfUsers(Long countOfUsers) {
            this.statisticDTO.countOfUsers = countOfUsers;
            return this;
        }

        public Builder countOfInspectors(Long countOfInspectors) {
            this.statisticDTO.countOfInspectors = countOfInspectors;
            return this;
        }

        public Builder countOfReports(Long countOfReports) {
            this.statisticDTO.countOfReports = countOfReports;
            return this;
        }

        public Builder processingReports(Long processingReports) {
            this.statisticDTO.processingReports = processingReports;
            return this;
        }

        public Builder approvedReports(Long approvedReports) {
            this.statisticDTO.approvedReports = approvedReports;
            return this;
        }

        public Builder disapprovedReports(Long disapprovedReports) {
            this.statisticDTO.disapprovedReports = disapprovedReports;
            return this;
        }

        public Builder countReportsPerYear(Map<Integer, Long> countReportsPerYear) {
            this.statisticDTO.countReportsPerYear = countReportsPerYear;
            return this;
        }

        public StatisticDTO build() {
            return this.statisticDTO;
        }
    }

    public Long getCountOfUsers() {
        return countOfUsers;
    }

    public void setCountOfUsers(Long countOfUsers) {
        this.countOfUsers = countOfUsers;
    }

    public Long getCountOfReports() {
        return countOfReports;
    }

    public void setCountOfReports(Long countOfReports) {
        this.countOfReports = countOfReports;
    }

    public Long getCountOfInspectors() {
        return countOfInspectors;
    }

    public void setCountOfInspectors(Long countOfInspectors) {
        this.countOfInspectors = countOfInspectors;
    }

    public Long getProcessingReports() {
        return processingReports;
    }

    public void setProcessingReports(Long processingReports) {
        this.processingReports = processingReports;
    }

    public Long getApprovedReports() {
        return approvedReports;
    }

    public void setApprovedReports(Long approvedReports) {
        this.approvedReports = approvedReports;
    }

    public Long getDisapprovedReports() {
        return disapprovedReports;
    }

    public void setDisapprovedReports(Long disapprovedReports) {
        this.disapprovedReports = disapprovedReports;
    }

    public Map<Integer, Long> getCountReportsPerYear() {
        return countReportsPerYear;
    }

    public void setCountReportsPerYear(Map<Integer, Long> countReportsPerYear) {
        this.countReportsPerYear = countReportsPerYear;
    }

    @Override
    public String toString() {
        return "StatisticDTO{" +
                "countOfUsers=" + countOfUsers +
                ", countOfReports=" + countOfReports +
                ", countOfInspectors=" + countOfInspectors +
                ", processingReports=" + processingReports +
                ", approvedReports=" + approvedReports +
                ", disapprovedReports=" + disapprovedReports +
                ", countReportsPerYear=" + countReportsPerYear +
                '}';
    }
}
