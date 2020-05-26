package managers.impl;

import managers.ISender;

import java.net.InetAddress;

public class Sender implements ISender {

    public void sendMessage(byte[] mess, InetAddress target) {
        System.out.println("Sended message " + new String(mess) + "\n" + "from address: "+ target.toString());
    }
}
