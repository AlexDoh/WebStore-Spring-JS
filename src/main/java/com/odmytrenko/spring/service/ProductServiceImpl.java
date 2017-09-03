package com.odmytrenko.spring.service;

import com.odmytrenko.spring.dao.ProductDao;
import com.odmytrenko.spring.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Set<Product> getAllByCategoryId(Long categoryId) {
        return productDao.getAllByCategoryId(categoryId);
    }

    @Override
    public Product create(Product user) {
        return null;
    }

    @Override
    public Product delete(Product user) {
        return null;
    }

    @Override
    public Product update(Product user) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Set<Product> getAll() {
        return null;
    }
}
