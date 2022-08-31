package com.my.persistence.entity;

public enum ReportStatus {
    PROCESSING("Processing"),
    APPROVED("Approved"),
    DISAPPROVED("Disapproved");

    private String statusName;

    ReportStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
