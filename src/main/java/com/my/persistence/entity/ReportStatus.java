package com.my.persistence.entity;

public enum ReportStatus {
    PROCESSING("Processing"),
    APPROVED("Approved"),
    DISAPPROVED("Disapproved");

    ReportStatus(String statusName) {
    }
}
