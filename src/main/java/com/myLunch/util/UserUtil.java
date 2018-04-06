package com.myLunch.util;

import com.myLunch.dto.UserTo;
import com.myLunch.model.User;

public class UserUtil {
    private UserUtil() {
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
