package com.my.persistence.dao;

import com.my.persistence.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserDAO extends BaseDAO<User>{

    boolean existsByEmail(String email);

    Optional<User> checkUserDetails(String email);

    Map<String, Long> getStatisticDataUsersCountByRoles();
}
