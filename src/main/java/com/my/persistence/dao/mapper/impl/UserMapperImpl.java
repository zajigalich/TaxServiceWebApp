package com.my.persistence.dao.mapper.impl;

import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.entity.EntrepreneurType;
import com.my.persistence.entity.User;
import com.my.persistence.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.my.persistence.dao.FieldDB.*;

public class UserMapperImpl implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(ENTITY_ID.name()))
                .name(resultSet.getString(USER_INFO_NAME.name()))
                .userRole(UserRole.valueOf(resultSet.getString(USER_ROLE_ID.name())))
                .entrepreneurType(EntrepreneurType.valueOf(resultSet.getString(USER_ENTREPRENEUR_TYPE_ID.name())))
                .lastName(resultSet.getString(USER_INFO_LASTNAME.name()))
                .email(resultSet.getString(USER_INFO_EMAIL.name()))
                .password(resultSet.getString(USER_PASSWORD.name()))
                .address(resultSet.getString(USER_ADDRESS.name()))
                .tin(resultSet.getString(USER_TIN.name()))
                .dateOfRegistration(resultSet.getDate(USER_REGISTRATION_TIME.name()))
                .build();
    }
}
