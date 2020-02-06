package com.taxi.service.validator;

import com.taxi.entity.UserEntity;
import com.taxi.service.PasswordEncryptor;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.reset;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Mock
    private Validator userValidator;

    @After
    public void resetMocks() {
        reset(userValidator);
    }

    @Test
    public void validatorShouldThrowExceptionIfPasswordIllegal() {
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Password do not match the pattern");
        UserEntity userEntity = UserEntity.builder()
                .withEmail("email1@gmail.com")
                .withPassword("")
                .build();
        userValidator.validate(userEntity);
    }

    @Test
    public void validatorShouldThrowExceptionIfEmailIllegal() {
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("Email do not match the pattern");
        UserEntity userEntity = UserEntity.builder()
                .withEmail("emailcom")
                .withPassword("P@ssword1")
                .build();
        userValidator.validate(userEntity);
    }

    @Test
    public void validatorShouldThrowExceptionIfUserIsNull() {
        expectedException.expect(ValidateException.class);
        expectedException.expectMessage("User is not exist");
        userValidator.validate(null);
    }

    @Test
    public void validatorShouldNotTrowException() {
        UserEntity userEntity = UserEntity.builder()
                .withEmail("email1@gmail.com")
                .withPassword("P@ssword1")
                .build();
        userValidator.validate(userEntity);
    }
}