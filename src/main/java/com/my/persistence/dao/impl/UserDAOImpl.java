package com.my.persistence.dao.impl;

import com.my.persistence.dao.ManagerDB;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.dao.mapper.impl.UserMapperImpl;
import com.my.persistence.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDAOImpl implements UserDAO {

    ObjectMapper<User> mapper = new UserMapperImpl();

    private final static String CREATE_USER = "INSERT INTO user " +
            "(role_id, entrepreneur_type_id, name, lastname, email, password, registration_time, tin, address) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String FIND_BY_ID = "SELECT * FROM USER WHERE id = ?;";
    private final static String FIND_BY_EMAIL = "SELECT * FROM USER WHERE email = ?;";
    private static final String FIND_ALL_USERS = "SELECT * FROM user;";
    private static final String STATISTIC_REPORTS_COUNT = "SELECT " +
            "       SUM(IF(u.role_id = 'User', 1, 0)) AS user_count, " +
            "       SUM(IF(u.role_id = 'Inspector', 1, 0)) AS inspector_count" +
            "       FROM user as u;";

    UserDAOImpl() {
    }

    @Override
    public User create(User user) {
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(CREATE_USER)) {
            int k = 0;
            statement.setInt(++k, user.getUserRole().ordinal() + 1);
            statement.setInt(++k, user.getEntrepreneurType().ordinal() + 1);
            statement.setString(++k, user.getName());
            statement.setString(++k, user.getLastName());
            statement.setString(++k, user.getEmail());
            statement.setString(++k, user.getPassword());
            statement.setDate(++k, user.getDateOfRegistration());
            statement.setInt(++k, Integer.parseInt(user.getTin()));
            statement.setString(++k, user.getAddress());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = null;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user = mapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                user = mapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement statement = con.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                users = new ArrayList<>();
                users.add(mapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean existsByEmail(String email) {
        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String, Long> getStatisticDataUsersCountByRoles() {

        Map<String, Long> data = new TreeMap<>();

        try (Connection con = ManagerDB.getInstance().getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(STATISTIC_REPORTS_COUNT)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                data.put("user_count", resultSet.getLong("user_count"));
                data.put("inspector_count", resultSet.getLong("inspector_count"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
