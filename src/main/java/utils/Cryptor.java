package utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Cryptor {
    private static Cipher cipher;
    private static SecretKey key;
    private static Cryptor instance;

    private Cryptor() {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            key = new SecretKeySpec("TaIsIia`s_SECkey".getBytes(),"AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public static Cryptor getInstance()
    {
        if (instance == null)
            instance = new Cryptor();
        return instance;
    }

    public byte [] decrypt(byte [] data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key,cipher.getParameters());
            return getResult(data);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }
    public byte [] encrypt(byte [] data) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return getResult(data);
        } catch (InvalidKeyException  e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getResult(byte [] data) {
        try {
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
