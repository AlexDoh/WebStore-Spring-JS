package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.Category;

import java.util.Set;

public interface CategoryDao extends GenericDao<Category> {

    Set<Category> getAll();
}
