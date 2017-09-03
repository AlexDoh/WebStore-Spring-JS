package com.odmytrenko.spring.service;

import com.odmytrenko.spring.model.Product;

import java.util.Set;

public interface ProductService extends GenericService<Product> {

    Set<Product> getAllByCategoryId(Long categoryId);
}
