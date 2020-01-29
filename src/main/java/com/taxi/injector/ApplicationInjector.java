package com.taxi.injector;

import com.taxi.dao.ConnectorDB;
import com.taxi.dao.UserDao;
import com.taxi.dao.impl.UserDaoImpl;
import com.taxi.entity.User;
import com.taxi.service.PasswordEncryptor;
import com.taxi.service.validator.UserValidator;
import com.taxi.service.validator.Validator;

public class ApplicationInjector {
    private static final ApplicationInjector INSTANCE = new ApplicationInjector();

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final PasswordEncryptor PASSWORD_ENCRIPTOR = new PasswordEncryptor();

    private static final ConnectorDB CONNECTOR = new ConnectorDB("database");

    private static final UserDao USER_REPOSITORY = new UserDaoImpl(CONNECTOR);

    private ApplicationInjector() {
    }

    public static ApplicationInjector getINSTANCE() {
        return INSTANCE;
    }

    public static Validator<User> getUserValidator() {
        return USER_VALIDATOR;
    }

    public static PasswordEncryptor getPasswordEncriptor() {
        return PASSWORD_ENCRIPTOR;
    }

    public static ConnectorDB getCONNECTOR() {
        return CONNECTOR;
    }

    public static UserDao getUserRepository() {
        return USER_REPOSITORY;
    }
}