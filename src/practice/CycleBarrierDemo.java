package practice;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycleBarrierDemo {

    static class Soldier implements Runnable{
        private String name;
        private CyclicBarrier cyclicBarrier;

        public Soldier(String name, CyclicBarrier cyclicBarrier) {
            this.name=name;
            this.cyclicBarrier=cyclicBarrier;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                //等待士兵集合完成
                cyclicBarrier.await();
                doWork();
                //士兵完成任务
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private void doWork() {
            // TODO Auto-generated method stub
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    static class Barrier implements Runnable{
        public int n;
        public boolean flag;

        public Barrier(int n,boolean flag) {
            // TODO Auto-generated constructor stub
            this.n=n;
            this.flag=flag;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            if(flag) {
                System.out.println("士兵["+n+"]个集合完毕"); 
                flag=false;
            }else {
                System.out.println("士兵["+n+"]个完成任务");
            }
        }
    }
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(10, new Barrier(10, true));
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i=0;i<10;++i) {
            es.submit(new Thread(new Soldier("士兵 "+i,cyclicBarrier)));
        }
        es.shutdown();
        //        int n=10;
        //        Thread[] allSoldier=new Thread[n];
        //        boolean flag=true;
        //        CyclicBarrier cyclicBarrier=new CyclicBarrier(n, new Barrier(n, flag));
        //        System.out.println("集合队伍");
        //        for(int i=0;i<10;i++) {
        //            System.out.println("士兵 "+i+"报道");
        //            allSoldier[i]=new Thread(new Soldier("士兵 "+i, cyclicBarrier));
        //            allSoldier[i].start();
        //        }
    }


}
