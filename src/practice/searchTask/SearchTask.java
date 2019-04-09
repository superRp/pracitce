package practice.searchTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchTask implements Callable<Integer>{

    public  int[] items;

    int value;

    int begin;

    int end;

    public SearchTask(int[] items,int value,int begin,int end) {
        this.items=items;
        this.begin=begin;
        this.end=end;
        this.value=value;
    }

    //private static int index=-1;

    private static AtomicInteger result=new AtomicInteger(-1);

    public int search(int value,int begin,int end) {
        if(result.get()>0) {
            return result.get();
        }
        for(int i=begin;i<end;i++) {
            if(items[i]==value) {
                if(!result.compareAndSet(-1, i)) {
                    System.out.println("坐标为"+i+"设置失败");
                }else {
                    System.out.println("坐标为"+i+"设置成功");
                }
                return result.get();
            }
            System.out.println("当前线程 "+Thread.currentThread().getId()+" 查找值  "+value+" 实际为 "+items[i]);
        }
        return result.get();
    }

    @Override
    public Integer call() throws Exception {
        // TODO Auto-generated method stub
        return search(value,begin,end);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<Integer>> re=new ArrayList<>();
        int items[]=new int [10000];
        for(int i=0;i<items.length;i++) {
            items[i]=(int) ((Math.random()*1000));
        }
        int size=items.length/2+1;
        for(int i=0;i<items.length;i+=size) {
            int j=i+size;
            if(j>items.length) 
                j=items.length;
            re.add(es.submit(new SearchTask(items,301,i,j)));
        }
        for(Future<Integer> f:re) {
            if(f.get()>0) {
                System.out.println("当前数组坐标为"+f.get()+" 值为"+items[f.get()]);
                es.shutdown();
            }
        }


    }


}
