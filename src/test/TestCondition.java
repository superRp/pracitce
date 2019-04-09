package test;

import practice.ReentenLockCondition;

public class TestCondition {
    public static void main(String[] args) throws InterruptedException {
        ReentenLockCondition tl=new ReentenLockCondition();
        Thread t1=new Thread(tl);
        t1.setName("t1");
        //        Thread t2=new Thread(condition);
        //        t2.setName("t2");
        t1.start();
        //        t2.start();
        //        t1.interrupt();
        //t2.interrupt();
        Thread.sleep(1);
        ReentenLockCondition.lock.tryLock();
        System.out.println("main lock");
        ReentenLockCondition.condition.signal();
        ReentenLockCondition.lock.unlock();
        
        System.out.println("main signal");

    }
}
