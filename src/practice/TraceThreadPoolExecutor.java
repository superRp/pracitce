package practice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor{

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
            TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void execute(Runnable command) {
        // TODO Auto-generated method stub
       super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }
    
    @Override
    public Future<?> submit(Runnable task) {
        // TODO Auto-generated method stub
        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }
    private Exception clientTrace() {
        return new Exception("client stack trace");
    }

    
    private Runnable wrap(Runnable task,Exception clientStack,String clientThreadName) {
        return new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    task.run();
                } catch (Exception e) {
                    // TODO: handle exception
                    clientStack.printStackTrace();
                    throw e;
                }
            }
        };
    }
}
