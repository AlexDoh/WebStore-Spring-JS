package com.odmytrenko.spring.service;

import com.odmytrenko.spring.model.User;

public interface UserService {

    User getByUsername(String username, String password);

    User findByToken(String token);

    User create(User user);

    User delete(User user);

    User update(User user);

    User findById(Long id);

}
