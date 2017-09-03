package com.odmytrenko.spring.service;

import com.odmytrenko.spring.model.User;

import java.util.Set;

public interface UserService {

    User getByUsernameAndPassword(String username, String password);

    User findByToken(String token);

    User create(User user);

    User delete(User user);

    User update(User user);

    User findById(Long id);

    Set<User> getAll();

}
