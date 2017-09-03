package com.odmytrenko.spring.service;

import com.odmytrenko.spring.dao.CategoryDao;
import com.odmytrenko.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category create(Category user) {
        return categoryDao.create(user);
    }

    @Override
    public Category delete(Category user) {
        return categoryDao.delete(user);
    }

    @Override
    public Category update(Category user) {
        return categoryDao.update(user);
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Set<Category> getAll() {
        return categoryDao.getAll();
    }

}
