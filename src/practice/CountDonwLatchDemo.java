package practice;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDonwLatchDemo implements Runnable{
    static CountDownLatch latch=new CountDownLatch(10);

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getId()+"has completed");
            latch.countDown();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        CountDonwLatchDemo demo=new CountDonwLatchDemo();
        for(int i=0;i<10;i++) {
            es.submit(demo);
        }
        //主线程等待10个线程全部完成
        latch.await();
        System.out.println("Fire");
        es.shutdown();
    }


}
