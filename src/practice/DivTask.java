package practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable{
    int a,b;
    public DivTask(int a,int b) {
        // TODO Auto-generated constructor stub
        this.a=a;
        this.b=b;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        double re=a/b;
        System.out.println(re);
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//                ExecutorService es=new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//                for(int i=0;i<5;i++) {
//                    //Future fu=es.submit(new DivTask(100, i));
//                    //fu.get();
//                    es.execute(new DivTask(100, i));
//                }

        TraceThreadPoolExecutor pools = new TraceThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
        for(int i=0;i<5;i++) {
            pools.execute(new DivTask(100, i));
        }
    }

}
