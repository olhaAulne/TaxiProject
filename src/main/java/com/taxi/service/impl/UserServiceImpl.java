package com.taxi.service.impl;

import com.taxi.dao.UserDao;
import com.taxi.domain.User;
import com.taxi.service.UserService;
import com.taxi.service.mapper.UserMapper;
import com.taxi.service.validator.Validator;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final Validator<User> validator;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper, Validator<User> validator) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.validator = validator;
    }

    @Override
    public void register(User user) {
        validator.validate(user);
        userDao.findByEmail(user.getEmail()).orElseThrow(() -> new RuntimeException());
        userDao.save(userMapper.mapUserToUserEntity(user));
    }

    @Override
    public User login(String email, String password) {
        return null;
    }
}
