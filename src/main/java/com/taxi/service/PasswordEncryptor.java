package com.taxi.service;


import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static javax.crypto.SecretKeyFactory.getInstance;

public class PasswordEncryptor {
    private static final int strength = 65536;
    private static final int length = 128;
    private static final byte[] salt = new byte[16];
    private static final String algorithm = "PBKDF2WithHmacSHA1";



    public String encrypt(String password)  {
        SecretKeyFactory skf = null;
        try {
            skf = getInstance(algorithm);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, strength, length);
            SecretKey key = skf.generateSecret(spec);
            return String.valueOf(key);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }
}
