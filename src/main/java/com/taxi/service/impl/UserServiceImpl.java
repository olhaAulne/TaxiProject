package com.taxi.service.impl;

import com.taxi.dao.UserDao;
import com.taxi.entity.User;
import com.taxi.service.PasswordEncryptor;
import com.taxi.service.UserService;
import com.taxi.service.validator.Validator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncryptor passwordEncryptor;
    private final Validator<User> userValidator;

    public UserServiceImpl(UserDao userDao, PasswordEncryptor passwordEncryptor, Validator<User> userValidator) {
        this.userDao = userDao;
        this.passwordEncryptor = passwordEncryptor;
        this.userValidator = userValidator;
    }

    @Override
    public boolean login(String email, String password) {
        String encryptPassword = passwordEncryptor.encrypt(password);

        return userDao.findByEmail(email)
                .map(user -> user.getPassword())
                .filter(pass -> pass.equals(encryptPassword))
                .isPresent();
    }

    @Override
    public User register(User user) {
        userValidator.validate(user);

        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User is exist");
        }

        userDao.save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).get();
    }
}
