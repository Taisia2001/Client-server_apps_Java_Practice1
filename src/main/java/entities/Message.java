package entities;

import org.json.JSONObject;
import utils.Cryptor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Message {
    private int cType;
    private int bUserId;
    private JSONObject messageJSON;
    private byte [] messageBytes;
    private int messageLength;

    public Message(int cType, int bUserId, String message){
        this.cType=cType;
        this.bUserId=bUserId;
        messageJSON = new JSONObject();
        messageJSON.put("message",message);
        createMessageBytes();
    }

    public int getbUserId() {
        return bUserId;
    }

    public int getcType() {
        return cType;
    }

    public JSONObject getMessageJSON() {
        return messageJSON;
    }

    public byte[] getMessageBytes() {
        return messageBytes;
    }

    private void createMessageBytes(){
        byte[] bytes = Cryptor.getInstance().encrypt(messageJSON.get("message").toString().getBytes(StandardCharsets.UTF_16BE));
        messageLength=bytes.length;
        messageBytes = ByteBuffer.allocate(8 + messageLength).
        order(ByteOrder.BIG_ENDIAN)
        .putInt(this.cType)
        .putInt(this.bUserId)
        .put(bytes).array();
    }




}
