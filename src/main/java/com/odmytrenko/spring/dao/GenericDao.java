package com.odmytrenko.spring.dao;

import java.util.Set;

public interface GenericDao<T> {

    T create(T t);

    T delete(T t);

    T update(T t);

    T findById(Long id);

    Set<T> getAll();
}
