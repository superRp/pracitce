package practice;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.management.loading.PrivateClassLoader;

public class ExtThreadPool {

    public static class Task implements Runnable{

        private String name;

        public Task(String name) {
            // TODO Auto-generated constructor stub
            this.name=name;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("正在执行：Thread ID:"+Thread.currentThread().getId()+" task Name:"+name);
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                // TODO Auto-generated method stub
                System.out.println("准备执行"+((Task)r).name);
            }
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                // TODO Auto-generated method stub
                System.out.println("执行完成"+((Task)r).name);
            }

            @Override
            protected void terminated() {
                // TODO Auto-generated method stub

                System.out.println("线程池退出");
            }
        };
        Task t=new Task("t");
        for(int i=0;i<5;i++) {
            tpe.execute(t);
        }
        tpe.shutdown();
    }
}
