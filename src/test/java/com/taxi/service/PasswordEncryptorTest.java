package com.taxi.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncryptorTest {
    private static final PasswordEncryptor PASSWORD_ENCRYPTOR = new PasswordEncryptor();

    @Test
    public void passwordEncryptorShouldReturnSameString() {
        String actualPassword = PASSWORD_ENCRYPTOR.encrypt("password1");
        String expectedPassword = PASSWORD_ENCRYPTOR.encrypt("password1");
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void passwordEncryptorShouldReturnDifferentString() {
        String actualPassword = PASSWORD_ENCRYPTOR.encrypt("pasword");
        String expectedPassword = PASSWORD_ENCRYPTOR.encrypt("password");
        assertNotEquals(expectedPassword, actualPassword);
    }
}