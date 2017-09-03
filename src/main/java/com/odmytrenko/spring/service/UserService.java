package com.odmytrenko.spring.service;

import com.odmytrenko.spring.model.User;

public interface UserService extends GenericService<User> {

    User getByUsernameAndPassword(String username, String password);

    User findByToken(String token);

}
