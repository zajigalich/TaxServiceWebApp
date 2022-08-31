package com.my.web.dto;

import java.lang.reflect.Field;

public class ReportFormError {

    private boolean isIncomeInvalid;
    private boolean isTaxRateInvalid;
    private boolean isPeriodInvalid;
    private boolean isYearInvalid;

    public boolean hasErrors() {
        return isIncomeInvalid || isPeriodInvalid || isYearInvalid || isTaxRateInvalid;
    }

    public boolean hasErrors(String field) {
        try {

            Field field1 = this.getClass().getDeclaredField(field);
            field1.setAccessible(true);

            return (Boolean) field1.get(this);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isIncomeInvalid() {
        return isIncomeInvalid;
    }

    public boolean isTaxRateInvalid() {
        return isTaxRateInvalid;
    }

    public boolean isPeriodInvalid() {
        return isPeriodInvalid;
    }

    public boolean isYearInvalid() {
        return isYearInvalid;
    }

    public void setIncomeInvalid(boolean incomeInvalid) {
        isIncomeInvalid = incomeInvalid;
    }

    public void setTaxRateInvalid(boolean taxRateInvalid) {
        isTaxRateInvalid = taxRateInvalid;
    }

    public void setPeriodInvalid(boolean periodInvalid) {
        isPeriodInvalid = periodInvalid;
    }

    public void setYearInvalid(boolean yearInvalid) {
        isYearInvalid = yearInvalid;
    }
}
