
import managers.Processor;
import managers.impl.Receiver;
import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReciverTest {
    @Test
    public void shouldPassWhenValidPacket(){

        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for(int i=0;i<24;++i){
            executorService.submit(()->new Receiver().receiveMessage());
        }
        executorService.shutdown();
        while (true)
            if(executorService.isTerminated()){
                Processor.shutdown();break;}
    }
}
