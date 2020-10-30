package com.web.security;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

class PasswordHasherTest {

    @Test
    void generateHash() {
        String pass = "password";
        String hashed = PasswordHasher.generateHash(pass);
        String pass2 = PasswordHasher.getHashed(pass,hashed);
        assertTrue(hashed == pass2);
    }
}