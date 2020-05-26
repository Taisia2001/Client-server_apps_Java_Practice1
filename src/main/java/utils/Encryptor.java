package utils;

import entities.Message;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    private static volatile Encryptor encryptor;

    private Cipher cipher;
    private SecretKey key;
    private Encryptor(){
        try {
            cipher = Cipher.getInstance("AES");
            key = new SecretKeySpec("TaIsIia`s_SECkey".getBytes(),"AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
    public static Encryptor getInstance(){
        if(encryptor ==null)
            synchronized (Encryptor.class){
                if(encryptor ==null)
                    encryptor =new Encryptor();}
        return encryptor;
    }


    public byte[] encrypt(Message message){
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, cipher.getParameters());
            return cipher.doFinal(message.getMessage().getBytes(StandardCharsets.UTF_16BE));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
