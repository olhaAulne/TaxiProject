package com.taxi.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {
    void save(E entity);

    Optional<E> findById(String id);

    List<E> findAll();

    void updateById(E entity);

    void deleteById(String id);
}
