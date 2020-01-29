package com.taxi.service;

import com.taxi.entity.User;

import java.util.List;

public interface UserService {
    boolean login(String email, String password);

    User register(User user);

    List<User> findAll();

    User findById(String id);

    User findByEmail(String email);
}
