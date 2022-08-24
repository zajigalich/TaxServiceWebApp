package com.my.persistence.dao.impl;

import com.my.persistence.dao.UserDAO;
import com.my.persistence.entity.User;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    @Override
    public User create(User entity) {
        return null;
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
    public User findById(String id) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public Optional<User> checkUserDetails(String email) {
        return Optional.empty();
    }

    @Override
    public Map<String, Long> getStatisticDataUsersCountByRoles() {
        return null;
    }
}
