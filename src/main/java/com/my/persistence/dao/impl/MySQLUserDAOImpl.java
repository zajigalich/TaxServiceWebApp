package com.my.persistence.dao.impl;

import com.my.exception.UserAlreadyExistsException;
import com.my.persistence.db.ManagerDB;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.mapper.ObjectMapper;
import com.my.persistence.dao.mapper.impl.UserMapperImpl;
import com.my.persistence.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySQLUserDAOImpl implements UserDAO {

    private final static Logger log = Logger.getLogger(MySQLUserDAOImpl.class);

    private final static ObjectMapper<User> mapper = new UserMapperImpl();

    private final static String CREATE_USER = "INSERT INTO user " +
            "(role_id, entrepreneur_type_id, name, lastname, email, password, tin, address ) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private final static String FIND_BY_ID = "SELECT * FROM USER WHERE id = ?;";

    private final static String DELETE_BY_ID = "DELETE FROM user u WHERE u.id = ?";

    private final static String FIND_BY_EMAIL = "SELECT * FROM USER WHERE email = ?;";

    private static final String FIND_ALL_USERS = "SELECT * FROM user;";

    private static final String STATISTIC_REPORTS_COUNT = "SELECT SUM(IF(u.role_id = 1, 1, 0)) AS user_count, "
            + "SUM(IF(u.role_id = 2, 1, 0)) AS inspector_count FROM user as u;";

    MySQLUserDAOImpl() {
    }

    @Override
    public boolean create(User user) {
        try (Connection con = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(CREATE_USER)) {
                int k = 0;
                statement.setInt(++k, user.getUserRole().ordinal() + 1);
                statement.setInt(++k, user.getEntrepreneurType().ordinal() + 1);
                statement.setString(++k, user.getName());
                statement.setString(++k, user.getLastName());
                statement.setString(++k, user.getEmail());
                statement.setString(++k, user.getPassword());
                statement.setString(++k, user.getTin());
                statement.setString(++k, user.getAddress());

                statement.execute();
                con.commit();
                return true;
            } catch (SQLException e) {
                log.error("Transaction fault");
                con.rollback();
                throw new UserAlreadyExistsException("User already exists");
            }
        } catch (SQLException e) {
            log.error("Unable to get Connection");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try (Connection con = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(FIND_BY_ID)) {

                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    user = mapper.extractFromResultSet(resultSet);
                }
                resultSet.close();
                con.commit();
            } catch (SQLException e) {
                log.error("Transaction fault");
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            log.error("Unable to get Connection");
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = null;
        try (Connection con = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(FIND_BY_EMAIL)) {

                statement.setString(1, email);
                //log.info("Query to sql(find by email): " + statement);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet != null && resultSet.next()) {
                    user = mapper.extractFromResultSet(resultSet);
                    resultSet.close();
                }

                con.commit();
            } catch (SQLException e) {
                log.error("Transaction fault");
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            log.error("Unable to get Connection");
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection con = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(DELETE_BY_ID)) {
                statement.setLong(1, id);

                statement.execute();
                con.commit();
                return true;
            } catch (SQLException e) {
                log.error("Transaction fault");
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            log.error("Unable to get Connection");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<User> findAll() {
        List<User> users = null;
        try (Connection con = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(FIND_ALL_USERS)) {
                ResultSet resultSet = statement.executeQuery();
                users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(mapper.extractFromResultSet(resultSet));
                }
                resultSet.close();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Map<String, Long> getStatisticDataUsersCountByRoles() {

        Map<String, Long> data = new TreeMap<>();

        try (Connection con = ManagerDB.getInstance().getConnection()) {
            try (PreparedStatement statement = con.prepareStatement(STATISTIC_REPORTS_COUNT)) {

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    data.put("user_count", resultSet.getLong("user_count"));
                    data.put("inspector_count", resultSet.getLong("inspector_count"));
                }
                resultSet.close();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
