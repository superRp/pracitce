package practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号   量
 * 
 *
 * @version Araf v1.0
 * @author ruipeng, 2018年11月12日
 */
public class SemapDemo implements Runnable{
    Semaphore sem=new Semaphore(5);

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            sem.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+"get done");
            sem.release();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(20);
         SemapDemo se=new SemapDemo();
        for(int i=0;i<20;i++) {
            es.submit(se);
            
        }
    }


}
