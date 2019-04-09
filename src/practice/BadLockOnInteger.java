package practice;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;

public class BadLockOnInteger implements Runnable{
    static Integer i=0;
    static BadLockOnInteger instance=new BadLockOnInteger();
    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int j=0;j<10000;j++) {
            synchronized (i) {
                i++;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
        
    }

}
