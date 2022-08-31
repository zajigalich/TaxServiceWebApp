package com.my.persistence.dao.mapper;

import com.my.persistence.entity.AbstractBaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjectMapper <E extends AbstractBaseEntity> {
    E extractFromResultSet(ResultSet resultSet) throws SQLException;
}
