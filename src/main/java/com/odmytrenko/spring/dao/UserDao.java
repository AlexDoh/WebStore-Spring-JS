package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.User;

public interface UserDao extends GenericDao<User> {

    User getByUsernameAndPassword(String username, String password);

    User findByToken(String token);

    User updateForUser(User user);

}
