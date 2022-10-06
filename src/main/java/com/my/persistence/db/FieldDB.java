package com.my.persistence.db;

public enum FieldDB {
    ENTITY_ID("id"),

    USER_ROLE_ID("role_id"),
    USER_ENTREPRENEUR_TYPE_ID("entrepreneur_type_id"),
    USER_INFO_NAME("name"),
    USER_INFO_LASTNAME("lastname"),
    USER_INFO_EMAIL("email"),
    USER_PASSWORD("password"),
    USER_REGISTRATION_TIME("registration_time"),
    USER_TIN("tin"),
    USER_ADDRESS("address"),

    REPORT_PERIOD_ID("period_id"),
    REPORT_YEAR("year"),
    REPORT_INCOME("income"),
    REPORT_TAX_RATE("tax_rate"),
    REPORT_DATE("report_date"),
    REPORT_STATUS_ID("status_id"),
    REPORT_COMMENT("comment"),
    REPORT_USER_ID("user_id");

    private final String field;

    FieldDB(String fieldName) {
        this.field = fieldName;
    }

    public String getField() {
        return field;
    }

}
