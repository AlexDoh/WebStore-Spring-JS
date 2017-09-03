package com.odmytrenko.spring.dao;

import com.odmytrenko.spring.model.Product;

import java.util.Set;

public interface ProductDao extends GenericDao<Product> {

    Set<Product> getAllByCategoryId(Long categoryId);

}
