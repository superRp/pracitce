package practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo_GC {

    static volatile  ThreadLocal<SimpleDateFormat> tl=new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected void finalize() throws Throwable {
            // TODO Auto-generated method stub
            System.out.println(this.toString()+" is gc");
        }
    };
    static  volatile  CountDownLatch latch=new CountDownLatch(10);
    public static class ParseDate implements Runnable{
        int i=0;
        public ParseDate(int i) {
            // TODO Auto-generated constructor stub
            this.i=i;  
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                if(tl.get()==null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
                        /**  */
                        private static final long serialVersionUID = 1L;

                        @Override
                        protected void finalize() throws Throwable {
                            // TODO Auto-generated method stub
                          System.out.println(this.toString()+" is gc");
                        }
                    });
                    System.out.println(i+" create SimpleDateFormat");
                }
                Date t=tl.get().parse("2019-01-17 16:27:"+i%60);
                System.out.println(i+" : "+t);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                //System.out.println(Thread.currentThread().getId()+": 计时器中还有"+latch.getCount()+"个");
                latch.countDown();
                
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++) {
            es.execute(new ParseDate(i));
        }
        latch.await();
        Thread.sleep(1000);
        System.out.println("mission complete");
        tl=null;
        System.gc();
        System.out.println("first GC complete");
        tl=new ThreadLocal<SimpleDateFormat>();
        latch=new CountDownLatch(10);
        for(int i=0;i<10;i++) {
            es.execute(new ParseDate(i));
        }
        latch.await();
//        System.out.println("第二次计时器中的数量"+latch.getCount());
        Thread.sleep(1000);
       System.gc();
        System.out.println("Second GC complete");
        es.shutdown();
        
    }

}
