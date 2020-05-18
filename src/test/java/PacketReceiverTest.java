import entities.Packet;
import exceptions.IllegalCRCException;
import org.junit.Assert;
import org.junit.Test;
import utils.CRC16;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class PacketReceiverTest {
    @Test
    public void shouldPassWhenValidPacket(){
        Packet p1 = new Packet((byte)1,1,1,"hello");
        PacketReceiver.decode(p1.getPacketBytes());
    }
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailBecauseOfMagicByte(){
        PacketReceiver.decode(new byte[]{0,0});
    }
    @Test(expected = IllegalCRCException.class)
    public void shouldFailBecauseOfPacketCRC(){
        Packet p1 = new Packet((byte)1,1,1,"hello");
        p1.getPacketBytes()[14]=0;
        p1.getPacketBytes()[15]=0;
        PacketReceiver.decode(p1.getPacketBytes());
    }
    @Test(expected = IllegalCRCException.class)
    public void shouldFailBecauseOfMessageCRC(){
        Packet p1 = new Packet((byte)1,1,1,"hello");
        p1.getPacketBytes()[p1.getPacketBytes().length-1]=0;
        p1.getPacketBytes()[p1.getPacketBytes().length-2]=0;
        Assert.assertEquals(ByteBuffer.wrap(p1.getPacketBytes(),14,2 ).order(ByteOrder.BIG_ENDIAN).getShort(), CRC16.get_CRC16(Arrays.copyOfRange(p1.getPacketBytes(), 0, 14)));
        PacketReceiver.decode(p1.getPacketBytes());
    }
}
