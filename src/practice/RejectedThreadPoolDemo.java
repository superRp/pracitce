package practice;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import practice.ThreadPoolDemo.Task;

public class RejectedThreadPoolDemo {
    public static class Mytask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println(System.currentTimeMillis()+":Thread ID:"+
            Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }
    }
    public static void main(String[] args) {
        Task t=new Task();
       ThreadPoolExecutor es = new ThreadPoolExecutor(5,5,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(10),new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // TODO Auto-generated method stub
            System.out.println(r.toString()+" is discard");
        }
    });
       for(int i=0;i<20;i++) {
           es.submit(t);
       }
    }

}
