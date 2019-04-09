package verify.producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable{
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static final int sleepTime=1000;
    private static AtomicInteger count=new AtomicInteger();
    public Producer(BlockingQueue<PCData> queue) {
        // TODO Auto-generated constructor stub
        this.queue=queue;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        PCData data=null;
        System.out.println("start producer id "+Thread.currentThread().getId());
        while(isRunning) {
            try {
                Thread.sleep(sleepTime);
                data=new PCData(count.incrementAndGet());
                System.out.println("data put in queue "+data);
                if(!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println("fail to put data "+data);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        
    }
    public void stop() {
        isRunning=false;
    }

}
