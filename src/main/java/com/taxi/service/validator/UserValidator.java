package com.taxi.service.validator;

import com.taxi.entity.User;

import java.util.function.Function;
import java.util.regex.Pattern;


public class UserValidator implements Validator<User> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");


    @Override
    public void validate(User user) {
        if (user == null) {
            throw new ValidateException("User is not exist");
        }
        validateEmail(user);
        validatePassword(user);
    }

    private static void validateEmail(User user) {
        validateString(EMAIL_PATTERN, user, User::getEmail, "Email do not match the pattern");
    }

    private static void validatePassword(User user) {
        validateString(PASSWORD_PATTERN, user, User::getPassword, "Password do not match the pattern");
    }

    private static void validateString(Pattern pattern, User user, Function<User, String> function,
                                       String exceptionMessage) {
        if (!pattern.matcher(function.apply(user)).matches()) {
            throw new ValidateException(exceptionMessage);
        }
    }
}
