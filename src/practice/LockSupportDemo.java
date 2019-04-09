package practice;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static Object o=new Object();
    public static class ObjectChangeThread extends Thread{
        public  ObjectChangeThread(String name) {
            // TODO Auto-generated constructor stub
            super.setName(name);
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized(o) {
                System.out.println("in "+getName());
                LockSupport.park();
                if(Thread.interrupted()) {
                    System.out.println(Thread.currentThread().getName()+" 执行中断");
                }
                System.out.println(getName()+" finished");
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ObjectChangeThread t1=new ObjectChangeThread("t1");
        ObjectChangeThread t2=new ObjectChangeThread("t2");
        t1.start();
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
//        t1.join();
//        t2.join();


    }
}
