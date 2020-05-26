package managers;

import entities.Message;
import managers.impl.Sender;
import utils.Decryptor;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Processor  implements Runnable{
    private final static ExecutorService service = Executors.newFixedThreadPool(6);
    Message message;
    public Processor(Message message) {
        this.message=message;
    }
    public static  void shutdown(){
        service.shutdown();
    }
    public static void process(Message message){
        service.submit(new Processor(message));
    }

    @Override
    public void run() {
        String incomingMessage =new String(Decryptor.getInstance().decrypt(message.getEncryptedMessage()), StandardCharsets.UTF_16BE);
        try {
            if (incomingMessage.equals("hello")) {
                new Sender().sendMessage(("#" + Thread.currentThread().getId() + " hello from here it\'s " + LocalDateTime.now()).getBytes(), Inet4Address.getLocalHost());
            } else
                new Sender().sendMessage(("#" + Thread.currentThread().getId() + "buy it\'s " + LocalDateTime.now()).getBytes(), Inet4Address.getLocalHost());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
