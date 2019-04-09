package verify.producer;

import java.text.MessageFormat;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;
    private static final int SLEEPTIME=1000;
    public  Consumer(BlockingQueue<PCData> queue) {
        // TODO Auto-generated constructor stub
        this.queue=queue;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("start Consumer id"+Thread.currentThread().getId());
        while(true) {
            try {
                PCData data = queue.take();
                if(data!=null) {
                    int result=data.getPcData()*data.getPcData();
                   Thread.sleep(SLEEPTIME);
                   System.out.println("take data from queue "+MessageFormat.format("{0}*{1}={2}",
                           data.getPcData(),data.getPcData(),result));
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        
    }

}
