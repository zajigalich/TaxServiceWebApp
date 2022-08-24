package com.my.persistence.entity;

import java.sql.Date;

public class User extends BaseEntity {

    private String email;

    private String password;

    private UserRole userRole;

    private EntrepreneurType entrepreneurType;

    private String name;

    private String lastName;

    private Date dateOfRegistration;

    private String ipn;
}
