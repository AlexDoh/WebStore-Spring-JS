package com.odmytrenko.spring.service;

import java.util.Set;

public interface GenericService<T> {

    T create(T user);

    T delete(T user);

    T update(T user);

    T findById(Long id);

    Set<T> getAll();
}
