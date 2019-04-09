package practice;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryDemo {
    public static class Task implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("执行一次线程方法");
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Task t=new Task();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                // TODO Auto-generated method stub
                Thread t=new Thread(r);
                t.setDaemon(true);
                System.out.println(t.getId()+"被设置为守护线程");
                return t;
            }
        });
        for(int i=0;i<5;i++) {
            tpe.submit(t);
        }
        Thread.sleep(2000);
    }
}
