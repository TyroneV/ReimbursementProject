package com.web.security;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordHasherTest {

    @Test
    void generateHash() {
        String pass = "password";
        String hashed = PasswordHasher.generateHash(pass);
        String pass2 = PasswordHasher.getHashed(pass,hashed);
        assertTrue(hashed.equals(pass2),"Password Hashing is working");
    }
}