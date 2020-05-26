package managers.impl;

import com.google.common.primitives.UnsignedLong;
import entities.Message;
import entities.Packet;
import managers.IReceiver;
import managers.Processor;
import utils.Commands;


public class Receiver implements IReceiver {

    @Override
    public void receiveMessage() {
        int command = Commands.values()[(int)(Math.random()*6)].ordinal();
        Packet p1 = new Packet((byte)(Math.random()*100), UnsignedLong.ONE,new Message(command, (int) (Math.random()*100),"hello"));
        Processor.process(p1.getMessage());

    }


}
