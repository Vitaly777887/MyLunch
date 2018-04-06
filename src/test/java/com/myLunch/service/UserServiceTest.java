package com.myLunch.service;


import com.myLunch.model.Role;
import com.myLunch.model.User;
import com.myLunch.service.AbstractServiceTest;
import com.myLunch.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static com.myLunch.UserTestData.ADMIN;
import static com.myLunch.UserTestData.USER;
import static com.myLunch.UserTestData.assertMatch;


public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "NewUser", EnumSet.of(Role.ROLE_USER), "new@ya.ru", "user", true, new Date());
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER.getId());
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    public void get() throws Exception {
        User result = service.get(ADMIN.getId());
        assertMatch(result, ADMIN);
    }

    @Test
    public void getAll() throws Exception {
        List<User> result = service.getAll();
        assertMatch(result, ADMIN, USER);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("Updated");
        updated.setPassword("newpass");
        updated.setRoles(EnumSet.of(Role.ROLE_ADMIN));
        service.update(updated);
        assertMatch(service.get(USER.getId()), updated);
    }

}