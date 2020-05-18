package entities;

import org.junit.Assert;
import org.junit.Test;

public class PacketTest {

    @Test
    public void successMagicByteCheck(){
        Assert.assertEquals(0x13, new Packet((byte)1,1,1,"hello").getPacketBytes()[0]);
    }
}
