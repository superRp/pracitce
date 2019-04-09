package practice;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Demo {
    public static void main(String[] args) throws Exception {

        
        ArrayList<Object> arrayList = new ArrayList<>();
        
        //1=100;
        
        //1+100
        
        //1+25 26+50;
        
        new Thread(  new Runnable() {
            public void run() {
                
            }
        }).start();
        
        Callable<Integer> aCallable1 =   new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 100;
            }
        };
        Callable<Integer> aCallable2 =  () ->100;
        
        
        FutureTask<Integer> it1 = new FutureTask<>(aCallable1);
        FutureTask<Integer> it2 = new FutureTask<>(aCallable2);
        
        it1.run();
        it2.run();
        
        Integer integer1 = it1.get();
        Integer integer2 = it2.get();
        System.out.println(integer1+integer2);
        
        String aString="";
        aString.contains("ss");
        aString=null;
        aString.contains("ss");
        
    }

}
