package practice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalTest {
    public static final int GEN_COUNT=10000000;
    public static final int THREAD_COUNT=4;
    static ExecutorService es=Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random rn=new Random(123);
    public static ThreadLocal<Random> trn=new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            // TODO Auto-generated method stub
            return new Random(123);
        }
    };
    public static class RndTask implements Callable<Long>{
        private int mode;
        public RndTask(int mode) {
            // TODO Auto-generated constructor stub
            this.mode=mode;
        }
        public Random getRandom() {
            if(mode==0) {
                return rn; 
            }else if(mode==1) {
                return trn.get();
            }else {
                return null;
            }
        }


        @Override
        public Long call() throws Exception {
            // TODO Auto-generated method stub
            long b = System.currentTimeMillis();
            for(int i=0;i<GEN_COUNT;i++) {
                getRandom().nextInt();
            }
            long c=System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" spend "+(c-b)+" millisecond");
            return c-b;
        }

    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Long>[] fus=new  Future[THREAD_COUNT];
        for(int i=0;i<THREAD_COUNT;i++) {
            fus[i]=es.submit(new RndTask(0));
        }
        long totalTime=0;
        for(int i=0;i<THREAD_COUNT;i++) {
            totalTime+=fus[i].get();
        }
        System.out.println("多线程访问同一共享变量耗时："+totalTime);
        totalTime=0;
        for(int i=0;i<THREAD_COUNT;i++) {
            fus[i]=es.submit(new RndTask(1));
        }
        for(int i=0;i<THREAD_COUNT;i++) {
            totalTime+=fus[i].get();
        }
        System.out.println("使用ThreadLocal包装实例："+totalTime);
        es.shutdown();
    }
}
