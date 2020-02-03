package com.taxi.dao;

import java.util.List;

public interface CrudPageableDao<E> extends CrudDao<E> {
    List<E> findAll(int page, int itemsPerPage);

    long count();
}
