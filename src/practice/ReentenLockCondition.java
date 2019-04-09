package practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentenLockCondition implements Runnable {
    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition=lock.newCondition();

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+":线程进入");
            if(  lock.tryLock()) {

                System.out.println(Thread.currentThread().getName()+" 上锁");
                condition.await();
                System.out.println(Thread.currentThread().getName()+" current thread is going on");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getName()+" current thread finished");
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+" runnable 完成");
        }

    }


}

