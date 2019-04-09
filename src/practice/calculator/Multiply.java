package practice.calculator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Multiply implements Runnable{
    public static BlockingQueue<Msg> bg=new LinkedBlockingQueue<>();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true) {
            try {
                Msg msg = bg.take();
                msg.msg=msg.i*msg.j+"";
                System.out.println("当前线程 "+Thread.currentThread().getId()+" 值为 "+msg.msg);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
