package com.my.service;

import com.my.exception.UserAlreadyExistsException;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.impl.DAOFactory;
import com.my.persistence.entity.EntrepreneurType;
import com.my.persistence.entity.User;
import org.apache.log4j.Logger;

public class RegistrationService {

    private static final Logger log = Logger.getLogger(RegistrationService.class);

    private static final UserDAO userDAO = DAOFactory.getUserDaoInstance();

    private static RegistrationService registrationService;

    public static synchronized RegistrationService getInstance() {

        if (registrationService == null)
            registrationService = new RegistrationService();

        return registrationService;
    }

    public boolean registerUser(User user) throws UserAlreadyExistsException {
        if (userDAO.findByEmail(user.getEmail()).isPresent()) {
            log.error("User with email(" + user.getEmail() + ") is already exist");
            throw new UserAlreadyExistsException("User already exist for " + user.getEmail());
        } else {
            userDAO.create(user);
            return true;
        }
    }

    public static boolean registerInspector(User inspector) {
        if (userDAO.findByEmail(inspector.getEmail()).isPresent()) {
            log.error("Inspector with email(" + inspector.getEmail() + ") is already exist");
            throw new UserAlreadyExistsException("Inspector already exist for " + inspector.getEmail());
        } else {
            inspector.setEntrepreneurType(EntrepreneurType.PHYSICAL_PERSON);
            userDAO.create(inspector);
            return true;
        }
    }
}
