
import com.google.common.primitives.UnsignedLong;
import exceptions.IllegalCRCException;
import utils.CRC16;
import utils.Decryptor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PacketReceiver {

    public static void decode(byte [] packet){
        if(packet[0]!=0x13)
            throw new IllegalArgumentException("Wrong magic byte!");
        System.out.println("Magic byte: " + packet[0]);
        final int srcId = packet[1]&0xFF;
        System.out.println("Client id: " + srcId);
        final UnsignedLong packetId = UnsignedLong.asUnsigned(ByteBuffer.wrap(packet,2,8 )
                .order(ByteOrder.BIG_ENDIAN)
                .getLong());
        System.out.println("Packet id: " + packetId);
        final int messageLength = ByteBuffer.wrap(packet,10,4 )
                .order(ByteOrder.BIG_ENDIAN)
                .getInt();
        System.out.println("Message length: "+messageLength);
        final short packetCrc = ByteBuffer.wrap(packet,14,2 )
                .order(ByteOrder.BIG_ENDIAN)
                .getShort();
        final short actualPacketCrc= CRC16.get_CRC16(Arrays.copyOfRange(packet, 0, 14));
        if(packetCrc!=actualPacketCrc)
            throw new IllegalCRCException("Illegal packet crc!!!");
        System.out.println("0-13 bytes crc: "+packetCrc);
        final int cType = ByteBuffer.wrap(packet,16,4 )
                .order(ByteOrder.BIG_ENDIAN)
                .getInt();
        System.out.println("cType: " + cType);
        final int bUserId = ByteBuffer.wrap(packet,20,4 )
                .order(ByteOrder.BIG_ENDIAN)
                .getInt();
        System.out.println("bUserId: " + bUserId);
        String message = new String(Decryptor.getInstance().decrypt(Arrays.copyOfRange(packet,24,16+messageLength)), StandardCharsets.UTF_16BE);
        System.out.println("Message: "+message);
        final short messageCrc = ByteBuffer.wrap(packet,16+messageLength,2 )
                .order(ByteOrder.BIG_ENDIAN)
                .getShort();
        final short actualMessageCrc= CRC16.get_CRC16(Arrays.copyOfRange(packet, 16, packet.length-2));
        if(messageCrc!=actualMessageCrc)
            throw new IllegalCRCException("Illegal message crc!!!");
        System.out.println("Message crc: "+messageCrc);
    }
}
