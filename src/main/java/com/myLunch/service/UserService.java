package com.myLunch.service;

import com.myLunch.AuthorizedUser;
import com.myLunch.model.User;
import com.myLunch.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService{

    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name");


    @Autowired
    private UserRepo userRepo;

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepo.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id){
        userRepo.deleteById(id);
    }

    public User get(int id){
        return userRepo.findById(id).orElse(null);
    }

    @Cacheable("users")
    public List<User> getAll(){
        return userRepo.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user){
        Assert.notNull(user, "User mustn't be null");
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getByEmail(email.toLowerCase());
        if (user == null)
            throw new UsernameNotFoundException("User with email" + email + "not found");
        return new AuthorizedUser(user);
    }
}
