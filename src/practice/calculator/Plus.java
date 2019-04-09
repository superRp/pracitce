package practice.calculator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Plus implements Runnable{
    public static BlockingQueue<Msg> bg=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
        try {
            Msg msg = bg.take();
            msg.i=msg.i+msg.j;
            Multiply.bg.add(msg);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }

}
