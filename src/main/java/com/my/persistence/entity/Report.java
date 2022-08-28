package com.my.persistence.entity;

import java.sql.Date;
import java.util.Objects;

public class Report extends AbstractBaseEntity {

    private Long userId;

    private Integer income;

    private TaxPeriod taxPeriod;

    private Integer taxRate;

    private Date reportDate;

    private Integer year;

    private Date lastChangeDate;

    private ReportStatus status;

    private String comment;

    public static Builder builder() {
        return new Report.Builder();
    }

    public static class Builder extends AbstractEntityBuilder<Report> {

        public Builder() {
            this.entity = new Report();
        }

        @Override
        public Builder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public Builder taxPeriod(TaxPeriod taxPeriod) {
            this.entity.setTaxPeriod(taxPeriod);
            return this;
        }

        public Builder year(Integer year) {
            this.entity.setYear(year);
            return this;
        }

        public Builder income(Integer income) {
            this.entity.setIncome(income);
            return this;
        }

        public Builder taxRate(Integer taxRate) {
            this.entity.setTaxRate(taxRate);
            return this;
        }

        public Builder reportDate(Date reportDate) {
            this.entity.setReportDate(reportDate);
            return this;
        }

        public Builder lastChangeDate(Date lastChangeDate) {
            this.entity.setLastChangeDate(lastChangeDate);
            return this;
        }

        public Builder status(ReportStatus status) {
            this.entity.setStatus(status);
            return this;
        }

        public Builder comment(String comment) {
            this.entity.setComment(comment);
            return this;
        }

        public Builder userId(Long userId) {
            this.entity.setUserId(userId);
            return this;
        }

        public Report build() {
            return this.entity;
        }

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public TaxPeriod getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(TaxPeriod taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return Objects.equals(id, report.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + income.hashCode();
        result = 31 * result + (taxPeriod != null ? taxPeriod.hashCode() : 0);
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        result = 31 * result + (reportDate != null ? reportDate.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (lastChangeDate != null ? lastChangeDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + userId +
                ", income=" + income +
                ", taxPeriod=" + taxPeriod +
                ", taxRate=" + taxRate +
                ", reportDate=" + reportDate +
                ", year=" + year +
                ", lastChangeDate=" + lastChangeDate +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }
}
