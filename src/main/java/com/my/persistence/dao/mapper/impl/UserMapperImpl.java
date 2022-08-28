package com.my.persistence.dao.mapper.impl;

import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.entity.EntrepreneurType;
import com.my.persistence.entity.User;
import com.my.persistence.entity.UserRole;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.my.persistence.db.FieldDB.*;

public class UserMapperImpl implements ObjectMapper<User> {

    private static final Logger log = Logger.getLogger(UserMapperImpl.class);

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong(ENTITY_ID.getField()))
                .name(resultSet.getString(USER_INFO_NAME.getField()))
                .userRole(UserRole.values()[resultSet.getInt(USER_ROLE_ID.getField()) - 1])
                .entrepreneurType(EntrepreneurType.values()[resultSet.getInt(USER_ENTREPRENEUR_TYPE_ID.getField()) - 1])
                .lastName(resultSet.getString(USER_INFO_LASTNAME.getField()))
                .email(resultSet.getString(USER_INFO_EMAIL.getField()))
                .password(resultSet.getString(USER_PASSWORD.getField()))
                .address(resultSet.getString(USER_ADDRESS.getField()))
                .tin(resultSet.getString(USER_TIN.getField()))
                .dateOfRegistration(resultSet.getDate(USER_REGISTRATION_TIME.getField()))
                .build();
    }
}
