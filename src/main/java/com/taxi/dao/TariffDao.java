package com.taxi.dao;

import com.taxi.entity.TariffEntity;

import java.util.Optional;

public interface TariffDao extends CrudDao<TariffEntity> {
    Optional<TariffEntity> findByTariffName(String name);
}
