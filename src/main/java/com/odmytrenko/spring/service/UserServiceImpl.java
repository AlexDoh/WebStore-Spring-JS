package com.odmytrenko.spring.service;

import com.odmytrenko.spring.dao.UserDao;
import com.odmytrenko.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    @Override
    public User updateForUser(User user) {
        return userDao.updateForUser(user);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Set<User> getAll() {
        return userDao.getAll();
    }
}
