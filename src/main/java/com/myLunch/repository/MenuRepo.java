package com.myLunch.repository;

import com.myLunch.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepo extends JpaRepository<MenuItem, Integer>{
}
