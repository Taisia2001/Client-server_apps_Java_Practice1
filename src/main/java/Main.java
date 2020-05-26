import managers.Processor;
import managers.impl.Receiver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for(int i=0;i<24;++i){
            executorService.submit(()->{
                Receiver r =new Receiver();
                r.receiveMessage();});
        }
        executorService.shutdown();
        while (true)
            if(executorService.isTerminated()){
                Processor.shutdown();break;}
        System.out.println("tasks gone");

    }
}
