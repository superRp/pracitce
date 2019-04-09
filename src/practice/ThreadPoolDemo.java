package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static class Task implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println(System.currentTimeMillis()+" Thread ID："+Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getId()+"开始运行");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
//        Task task =new Task();
//        ExecutorService es = Executors.newCachedThreadPool();
//        for(int i=0;i<10;i++) {
//            es.submit(task);
//        }
        ExecutorService es= new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, 
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
                    
                    @Override
                    public Thread newThread(Runnable r) {
                        // TODO Auto-generated method stub
                        Thread t=new Thread(r);
                        t.setDaemon(true);
                        return t;
                    }
                });
        for(int i=0;i<5;i++) {
            es.submit(new Task());
        }
        Thread.sleep(2000);
    }
}
