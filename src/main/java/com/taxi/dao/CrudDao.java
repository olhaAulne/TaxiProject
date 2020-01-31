package com.taxi.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {
    void save(E entity);

    Optional<E> findById(String id);

    void update(E entity);

    void deleteById(String id);
}
