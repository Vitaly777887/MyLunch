package com.myLunch;

import com.myLunch.model.AbstractBaseEntity;
import com.myLunch.model.Role;
import com.myLunch.model.User;

import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = AbstractBaseEntity.START_SEQUENCE;
    public static final User USER = new User(USER_ID, "User", EnumSet.of(Role.ROLE_USER), "user@yandex.ru", "password", true, new Date());
    public static final User ADMIN = new User(USER_ID + 1, "Admin", EnumSet.of(Role.ROLE_ADMIN), "admin@gmail.com", "admin", true, new Date());

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
    }
}
