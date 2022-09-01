package com.my.persistence.entity;

public enum EntrepreneurType {
    PHYSICAL_PERSON("Physical Person"),
    JURIDICAL_PERSON("Juridical Person");

    private String type;

    EntrepreneurType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
