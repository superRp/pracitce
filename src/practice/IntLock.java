package practice;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock implements Runnable {
    ReentrantLock lock1=new ReentrantLock();
    ReentrantLock lock2=new ReentrantLock();
    int lock;
    public IntLock(int lock) {
        this.lock=lock;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            if(lock==1) {
                lock1.lockInterruptibly();
                Thread.sleep(5000);
                lock2.lockInterruptibly();
            }else {
                lock2.lockInterruptibly();
                Thread.sleep(5000);
                lock1.lockInterruptibly();
            }
            System.out.println(Thread.currentThread().getName()+"执行完毕");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
        }
    }
    public static void main(String[] args) {
        IntLock lock1=new IntLock(1);
        IntLock lock2=new IntLock(2);
        Thread t1=new Thread(lock1);
        t1.setName("t1");
        Thread t2=new Thread(lock2);
        t2.setName("t2");
        t1.start();
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t2.interrupt();
    }

}
