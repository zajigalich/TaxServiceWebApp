package com.my.persistence.entity;

import java.sql.Date;

public class Report extends BaseEntity {

    private Long userId;

    private Integer income;

    private TaxPeriod taxPeriod;

    private Integer taxRate;

    private Date reportDate;

    private Status status;

    private String comment;

}
