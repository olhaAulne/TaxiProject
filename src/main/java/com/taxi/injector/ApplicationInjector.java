package com.taxi.injector;

import com.taxi.command.Command;
import com.taxi.command.user.LoginCommand;
import com.taxi.dao.HikariConnection;
import com.taxi.dao.UserDao;
import com.taxi.dao.impl.UserDaoImpl;
import com.taxi.domain.User;
import com.taxi.service.PasswordEncryptor;
import com.taxi.service.UserService;
import com.taxi.service.impl.UserServiceImpl;
import com.taxi.service.mapper.UserMapper;
import com.taxi.service.validator.UserValidator;
import com.taxi.service.validator.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationInjector {
    private static final ApplicationInjector INSTANCE = new ApplicationInjector();

    private static final HikariConnection DB_CONNECTOR = new HikariConnection("database");

    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);

    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();

    private static final UserMapper USER_MAPPER = new UserMapper(PASSWORD_ENCRYPTOR);

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, PASSWORD_ENCRYPTOR, USER_VALIDATOR);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("login", LOGIN_COMMAND);

        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    private static ApplicationInjector applicationInjector;


    private ApplicationInjector() {
    }

    public static ApplicationInjector getInstance() {
        if (applicationInjector == null) {
            synchronized (ApplicationInjector.class) {
                if (applicationInjector == null) {
                    applicationInjector = new ApplicationInjector();
                }
            }
        }
        return applicationInjector;
    }
    public UserService getUserService() {
        return USER_SERVICE;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

}
