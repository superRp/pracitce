package verify.ThreadLocalTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoSafeThread implements Runnable{
    private static  ThreadLocal<Number> tl=new ThreadLocal<>();
    public static int i=0;
    static Number n=new Number();

    @Override
    public void run() {
        // TODO Auto-generated method stub
        n.setNumber(i++);
        tl.set(n);
        System.out.println(tl.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getId()+" number: "+tl.get().getNumber());
    }
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++) {
            pool.submit(new NoSafeThread());
        }
        pool.shutdown();
       
    }

}
