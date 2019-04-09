package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long>{

    private static final int  threshold=10000;
    private long start;
    private long end;
    public CountTask(long start,long end) {
        // TODO Auto-generated constructor stub
        this.start=start;
        this.end=end;
    }
    @Override
    protected Long compute() {
        long sum=0;
        boolean isCompute=(end - start)>threshold;
        if(!isCompute) {
            for(long i=start;i<=end;i++) {
                sum+=i;
            }
            return sum;
        }else {
            List<CountTask> tasks=new ArrayList<CountTask>();
            //分成100步
            long step=(end-start)/100;
            long pos=start;
            for(int i=0;i<100;i++) {
                long lastone=pos+step;
                if(lastone>end) {
                    lastone=end;
                }
                CountTask countTask = new CountTask(pos,lastone); 
                tasks.add(countTask);
                pos+=step+1;
                countTask.fork();
            }
            for(int i=0;i<tasks.size();i++) {
                sum+=tasks.get(i).join();
            }
            return sum;

        }

    }
    public static void main(String[] args) {
        ForkJoinPool pool=new ForkJoinPool();
        CountTask task=new CountTask(0, 200000L);
        ForkJoinTask<Long> submit = pool.submit(task);
        try {
            Long sum = submit.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
