package practice;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrLock implements Runnable{

    ReentrantLock lock=new ReentrantLock();
    static int i=0;
    @Override
    public void run() {
        // TODO Auto-generated method stub
        for(int j=0;j<10000;j++) {
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
            }      
        }   
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrLock lock=new ReentrLock();
        Thread t1=new Thread(lock);
        Thread t2=new Thread(lock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
