package utils;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class CryptorTest {
    @Test
    public void successfulDecryptEncrypted(){
        String s = "my new Message";
        Assert.assertEquals(s, new String(Cryptor.getInstance().decrypt(Cryptor.getInstance().encrypt(s.getBytes(StandardCharsets.UTF_16BE))),StandardCharsets.UTF_16BE));

    }
}
