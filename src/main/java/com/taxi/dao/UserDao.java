package com.taxi.dao;

import com.taxi.entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User> {
    Optional<User> findByEmail(String email);
}
