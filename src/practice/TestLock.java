package practice;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock implements Runnable {
    
    public ReentrantLock lock=new ReentrantLock();
    

    @Override
    public void run() {
        // TODO Auto-generated method stub
        lock.lock();
        int a=1+1;
        System.out.println(Thread.currentThread().getId()+" a="+a);
        lock.unlock();
    }
    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        Thread t=new Thread(testLock);
        Thread t2=new Thread(testLock);
        t.start();
        t2.start();
    }

}
