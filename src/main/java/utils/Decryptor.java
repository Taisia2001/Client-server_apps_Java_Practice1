package utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decryptor {
    private static volatile Decryptor decryptor;
    private Cipher cipher;
    private SecretKey key;
    private Decryptor(){
        try {
            cipher = Cipher.getInstance("AES");
            key = new SecretKeySpec("TaIsIia`s_SECkey".getBytes(),"AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }
    public static Decryptor getInstance(){
        if(decryptor ==null)
            synchronized (Decryptor.class){
            if(decryptor ==null)
                decryptor =new Decryptor();}
        return decryptor;
    }
    public byte [] decrypt(byte [] message){
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(message);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }  catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}