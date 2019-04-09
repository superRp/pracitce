package practice;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {
    static AtomicInteger in=new AtomicInteger();
    public static class AddThread implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for(int i=0;i<10000;i++) {
                in.incrementAndGet();
            }
        }

    }    
    public static void main(String[] args) throws InterruptedException {
        Thread[] ts=new Thread[10];
        for(int i=0;i<10;i++) {
            ts[i]=new Thread(new AddThread());
        }
        for(int i=0;i<10;i++) {
            ts[i].start();
        }
        for(int i=0;i<10;i++) {
            ts[i].join();
        }
        System.out.println("最终的值为"+in.get());
    }
    
}
