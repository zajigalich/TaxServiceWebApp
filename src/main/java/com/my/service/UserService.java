package com.my.service;

import com.my.exception.UserNotFoundException;
import com.my.exception.WrongPasswordException;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.impl.DAOFactory;
import com.my.persistence.entity.TaxPeriod;
import com.my.persistence.entity.User;
import com.my.web.EntityDTOUtil;
import com.my.web.dto.UserDTO;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserService {

    private final static UserDAO userDAO = DAOFactory.getUserDaoInstance();

    private final static Logger log = Logger.getLogger(UserService.class);

    private static UserService userService;

    public static synchronized UserService getInstance() {
        if (userService == null)
            userService = new UserService();

        return userService;
    }

    public User validateLoginData(String email, String password)
            throws WrongPasswordException, UserNotFoundException {

        Optional<User> user = userDAO.findByEmail(email);
        log.info("Validation login data for input: " + email +", " + password);
        if (user.isPresent()) {
            if (!(user.get().getPassword().equals(password))) {
                log.error("Wrong password input for " + email);
                throw new WrongPasswordException("Wrong password");
            }
        }
        return user.orElseThrow(() -> {
            log.error("No such user found with email" + email);
            return new UserNotFoundException("No such user found");
        });
    }

    public UserDTO getUserInformationById(Long userId) throws UserNotFoundException {
        return userDAO.findById(userId)
                .map(EntityDTOUtil::convertUserEntityToDto)
                .orElseThrow(() -> new UserNotFoundException("No user found by id"));

    }
}
