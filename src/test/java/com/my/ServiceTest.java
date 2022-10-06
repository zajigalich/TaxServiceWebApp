package com.my;

import com.my.exception.UserAlreadyExistsException;
import com.my.exception.UserNotFoundException;
import com.my.exception.WrongPasswordException;
import com.my.persistence.dao.UserDAO;
import com.my.persistence.dao.impl.DAOFactory;
import com.my.persistence.entity.EntrepreneurType;
import com.my.persistence.entity.User;
import com.my.persistence.entity.UserRole;
import com.my.service.RegistrationService;
import com.my.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class ServiceTest {

    @Test
    public void registrationServiceTest() {
        List<User> users = new ArrayList<>();
        User testUser = User.builder()
                .name("TestUser")
                .lastName("TestUser")
                .userRole(UserRole.USER)
                .entrepreneurType(EntrepreneurType.PHYSICAL_PERSON)
                .email("t@t.t")
                .password("123456")
                .address("test")
                .tin("00000000")
                .build();

        users.add(testUser);

        User testInspector = User.builder()
                .userRole(UserRole.USER)
                .email("tt@t.t")
                .password("123456")
                .build();

        users.add(testInspector);

        Assert.assertTrue(RegistrationService.registerUser(testUser));
        Assert.assertThrows(UserAlreadyExistsException.class, () -> RegistrationService.registerUser(testUser));
        Assert.assertTrue(RegistrationService.registerInspector(testInspector));
        Assert.assertThrows(UserAlreadyExistsException.class, () -> RegistrationService.registerInspector(testUser));

        users.forEach(this::deleteUser);
    }

    private void deleteUser(User user) {
        UserDAO userDAO = DAOFactory.getUserDaoInstance();
        userDAO.delete(userDAO.findByEmail(user.getEmail()).get().getId());
    }

    private boolean compareUsers(User user1, User user2) {
        AtomicBoolean isEqual = new AtomicBoolean(true);
        user1.setId(user2.getId());

        Arrays.stream(user1.getClass().getDeclaredFields()).forEach(field -> {
            try {
                if (!field.get(user1).equals(field.get(user2))) {
                    isEqual.set(false);
                }
            } catch (IllegalAccessException ignored) {
            }
        });
        return isEqual.get();
    }

    @Test
    public void userServiceTest() {
        UserService userService = UserService.getInstance();

        User testUser = User.builder()
                .name("TestUser")
                .lastName("TestUser")
                .userRole(UserRole.USER)
                .entrepreneurType(EntrepreneurType.PHYSICAL_PERSON)
                .email("t@t.t")
                .password("123456")
                .address("test")
                .tin("00000000")
                .build();

        RegistrationService.registerUser(testUser);

        Assert.assertTrue(compareUsers(testUser,
                userService.validateLoginData(testUser.getEmail(), testUser.getPassword())));

        Assert.assertThrows(UserNotFoundException.class,
                () -> userService.validateLoginData(testUser.getEmail() + 99999, testUser.getPassword()));

        Assert.assertThrows(WrongPasswordException.class,
                () -> userService.validateLoginData(testUser.getEmail(), testUser.getPassword() + 9999));


        this.deleteUser(testUser);
    }
}
