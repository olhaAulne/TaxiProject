package com.taxi.service;

import com.taxi.domain.User;


public interface UserService {
    void register(User user);

    User login(String email, String password);
}
