package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.User;

import java.util.Set;

public interface UserDao extends GenericDao<User> {

    User getByUsernameAndPassword(String username, String password);

    User findByToken(String token);

    Set<User> getAll();

}
