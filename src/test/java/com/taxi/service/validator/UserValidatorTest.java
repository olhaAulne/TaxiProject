package com.taxi.service.validator;

import com.taxi.entity.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private static Validator userValidator = new UserValidator();


    @Test
    public void validatorShouldThrowExceptionIfPasswordIllegal() {
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Password do not match the pattern");
        User user = User.builder()
                .withEmail("email1@gmail.com")
                .withPassword("")
                .build();
        userValidator.validate(user);
    }

    @Test
    public void validatorShouldThrowExceptionIfEmailIllegal() {
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Email do not match the pattern");
        User user = User.builder()
                .withEmail("emailcom")
                .withPassword("P@ssword1")
                .build();
        userValidator.validate(user);
    }

    @Test
    public void validatorShouldThrowExceptionIfUserIsNull() {
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("User is not exist");
        userValidator.validate(null);
    }

    @Test
    public void validatorShouldNotTrowException() {
        User user = User.builder()
                .withEmail("email1@gmail.com")
                .withPassword("P@ssword1")
                .build();
        userValidator.validate(user);
    }
}