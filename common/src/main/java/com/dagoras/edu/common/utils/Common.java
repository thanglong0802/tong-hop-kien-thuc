package com.dagoras.edu.common.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Common {

    private static final String secret = "secret12345";

    public static String encrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] bytes = cipher.doFinal(str.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String decrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(bytes);
    }
}
