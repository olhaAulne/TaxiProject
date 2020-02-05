package com.taxi.service;

import com.taxi.domain.User;

import java.util.List;


public interface UserService {
    void register(User user);

    User login(String email, String password);

    List<User> findAll(int pageNumber);

    User findById(String id);

    User findByEmail(String email);
}
