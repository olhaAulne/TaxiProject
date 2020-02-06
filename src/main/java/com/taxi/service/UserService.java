package com.taxi.service;

import com.taxi.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    void register(User user);

    Optional<User> login(String email, String password);

    List<User> findAll(int pageNumber);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);
}
