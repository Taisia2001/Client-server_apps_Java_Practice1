package entities;

import com.google.common.primitives.UnsignedLong;
import utils.CRC16;

import java.nio.ByteBuffer;

public class Packet {

    private static final byte bMagic = 0x13;
    private byte bSrc;
    private UnsignedLong bPktId;
    private Message message;
    private byte [] packetBytes;
    public Packet(byte bSrc,UnsignedLong bPktId, Message message){
        this.bSrc=bSrc;
        this.message =  message;
        this.bPktId=bPktId;
        createBytePacket();

    }
    void createBytePacket(){
        byte [] bb = ByteBuffer.allocate(14)
                .put(bMagic)
                .put(bSrc)
                .putLong(bPktId.longValue())
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

    public Message getMessage() {
        return message;
    }
}
