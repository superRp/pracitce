package practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteLockDemo {
    private static Lock lock=new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    private static ReadLock readLock=readWriteLock.readLock();
    private static WriteLock writeLock=readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId()+" read job done");
            Thread.sleep(1000);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public Object handleWrite(Lock lock,int index) throws InterruptedException {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId()+"write job done");
            Thread.sleep(1000);
            value=index;
            return value;
        }finally {
            lock.unlock();
        } 
    }
    public static void main(String[] args) {
        ReadWriteLockDemo demo=new ReadWriteLockDemo();
        Runnable readRunnable=new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    demo.handleRead(readLock);
                    //demo.handleRead(lock);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        Runnable writeRunnable=new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    demo.handleWrite(writeLock, 12);
                    //demo.handleWrite(lock, 12);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }    
            }
        };
        long time=System.currentTimeMillis();
        for(int i=0;i<18;i++) {
            new Thread(readRunnable).start();
        }
        for(int i=18;i<20;i++) {
            new Thread(writeRunnable).start();
        }
        System.out.println("耗时："+(System.currentTimeMillis()-time));

    }

}
