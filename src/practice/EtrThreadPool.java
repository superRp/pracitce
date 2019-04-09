package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EtrThreadPool {

    public static class MyTask implements Runnable{

        private String name;
        
        public MyTask(String name) {
            // TODO Auto-generated constructor stub
            this.name=name;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("当前执行：Thread ID"+Thread.currentThread().getId()+",TaskName="+name); 
        }
    }
    public static void main(String[] args) {
        ExecutorService es=new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                // TODO Auto-generated method stub
              System.out.println("准备执行"+((MyTask)r).name);
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                // TODO Auto-generated method stub
                System.out.println("执行完成"+((MyTask)r).name);
            }
            @Override
            protected void terminated() {
                // TODO Auto-generated method stub
                System.out.println("线程池退出");
            }
        };
       for(int i=0;i<5;i++) {
           MyTask mt=new MyTask("Task"+i);
           es.execute(mt);
       }
       es.shutdown();
    }
    
}
