package practice;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {
    static List al=new ArrayList<>();

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            for(int i=0;i<10000;i++) {
                al.add(i);
            }
            
        } 
    }
    public static void main(String[] args) throws InterruptedException {
        AddThread addThread=new AddThread();
        Thread t1=new Thread(addThread);
        Thread t2=new Thread(addThread);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
