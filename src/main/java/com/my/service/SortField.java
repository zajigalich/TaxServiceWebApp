package com.my.service;

public enum SortField {
    YEAR_OLD("Year old", "year", "asc"),
    YEAR_NEW("Year new", "year", "desc"),

    DATE_OLD("Date old", "report_date", "asc"),
    DATE_NEW("Date new", "report_date", "desc");

    public final String sortName;
    public final String fieldInTable;
    public final String direction;

    SortField(String sortName, String fieldInTable, String direction) {
        this.sortName = sortName;
        this.fieldInTable = fieldInTable;
        this.direction = direction;
    }
}