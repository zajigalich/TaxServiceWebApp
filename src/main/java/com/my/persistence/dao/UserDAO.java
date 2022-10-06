package com.my.persistence.dao;

import com.my.persistence.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserDAO extends BaseDAO<User>{

//    boolean validate(String email, String pass);

    //boolean createInspector(User user);

    Optional<User> findByEmail(String email);

    Map<String, Long> getStatisticDataUsersCountByRoles();
}
