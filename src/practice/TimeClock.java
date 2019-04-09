package practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimeClock implements Runnable {
  
    public static void main(String[] args) {
        TimeClock t=new TimeClock();
        Thread t1=new Thread(t);
        Thread t2=new Thread(t);
        t1.start();
        t2.start();
       
    }
    public ReentrantLock lock=new ReentrantLock();
    

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            if(lock.tryLock(2, TimeUnit.SECONDS)) {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getId()+"获得锁");
                
            }else {
                System.out.println(Thread.currentThread().getId()+"get lock failed");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        
    }
    

}
