package com.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    public static String generateHash(String password){
        String hashedPassword = "";
        byte[] salt;
        try {
            salt = getSalt();
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            hashedPassword = getSecurePassword(password,salt) + "." + encodedSalt;
        } catch (Exception e){
            System.out.println("Salting failed");
        }
        return hashedPassword;
    }

    public static String getHashed(String password, String hashedPass){
        String inputHashPass;
        String saltString = hashedPass.replaceAll("\\w+[.]","");
        byte[] salt = Base64.getDecoder().decode(saltString);
        inputHashPass = getSecurePassword(password,salt) +"."+saltString;
        return inputHashPass;
    }


    private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
