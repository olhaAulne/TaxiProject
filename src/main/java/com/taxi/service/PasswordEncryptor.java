package com.taxi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static javax.crypto.SecretKeyFactory.getInstance;

public class PasswordEncryptor {
    private static final Logger LOGGER = LogManager.getLogger(PasswordEncryptor.class);


    private static final int STRENGTH = 65536;
    private static final int LENGTH = 128;
    private static final byte[] SALT = new byte[16];
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";



    public String encrypt(String password)  {
        SecretKeyFactory skf = null;
        try {
            skf = getInstance(ALGORITHM);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), SALT, STRENGTH, LENGTH);
            SecretKey key = skf.generateSecret(spec);
            return String.valueOf(key);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error(e.getMessage());
        }

        return null;
    }
}
