package entities;

import utils.CRC16;
import utils.Cryptor;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Packet {

    private static final byte bMagic = 0x13;
    private byte bSrc;
    private static long bPktId;
    private Message message;
    private byte [] packetBytes;
    public Packet(byte bSrc, int cType, int bUserId, String message){
        this.bSrc=bSrc;
        this.message = new Message(cType, bUserId, message);
        createBytePacket();
        bPktId++;
    }
    void createBytePacket(){
        byte [] bb = ByteBuffer.allocate(14)
                .put(bMagic)
                .put(bSrc)
                .putLong(bPktId)
                .putInt(message.getMessageBytes().length)
                .array();
        packetBytes = ByteBuffer.allocate(18+message.getMessageBytes().length)
                .put(bb)
                .putShort(CRC16.get_CRC16(bb))
                .put(message.getMessageBytes())
                .putShort(CRC16.get_CRC16(message.getMessageBytes()))
                .array();
    }

    public byte[] getPacketBytes() {
        return packetBytes;
    }
}
