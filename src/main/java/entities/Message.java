package entities;


import utils.Encryptor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Message {
    private int cType;
    private int bUserId;
    private String message;
    private byte [] messageBytes;
    private byte [] encryptedMessage;

    public Message(int cType, int bUserId, String message){
        this.cType=cType;
        this.bUserId=bUserId;
        this.message= message;
        createMessageBytes();
    }

    public int getbUserId() {
        return bUserId;
    }

    public int getcType() {
        return cType;
    }


    public byte[] getMessageBytes() {
        return messageBytes;
    }

    public byte[] getEncryptedMessage() {
        return encryptedMessage;
    }

    public String getMessage() {
        return message;
    }

    private void createMessageBytes(){
        encryptedMessage = Encryptor.getInstance().encrypt(this);
        messageBytes = ByteBuffer.allocate(8 + encryptedMessage.length).
        order(ByteOrder.BIG_ENDIAN)
        .putInt(this.cType)
        .putInt(this.bUserId)
        .put(encryptedMessage).array();
    }


}
