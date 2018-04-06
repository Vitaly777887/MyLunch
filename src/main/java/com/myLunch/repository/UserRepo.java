package com.myLunch.repository;

import com.myLunch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getByEmail(String email);
    Optional<User> findById(int id);
}
