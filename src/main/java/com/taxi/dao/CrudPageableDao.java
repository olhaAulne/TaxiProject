package com.taxi.dao;

import java.util.List;

public interface CrudPageableDao<E> extends CrudDao<E> {
    List<E> findAll(Page page);

    Long count();
}
