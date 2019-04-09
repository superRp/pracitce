package practice;

import java.util.concurrent.locks.ReentrantLock;

public class TryLock implements Runnable{
    public static ReentrantLock lock1=new ReentrantLock();
    public static ReentrantLock lock2=new ReentrantLock();
    int lock;
    public TryLock(int lock) {
        // TODO Auto-generated constructor stub
        this.lock=lock;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        if(lock==1) {
            while(true) {
                if(lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if(lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId()+": My job done"); 
                                return;}
                            finally {
                                lock2.unlock();
                            }
                        }
                    }
                    finally {
                        lock1.unlock();
                    }  
                }   
            }
        }else if(lock==2){
            while(true) {
                if(lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if(lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getId()+":My Job Done");
                                return;
                            }finally {
                                lock1.unlock();
                            }
                        }
                    }finally {
                        lock2.unlock();
                    }
                }
            }

        }
    }
   public static void main(String[] args) {
    TryLock try1=new TryLock(1);
    TryLock try2=new TryLock(2);
    Thread t1=new Thread(try1);
    Thread t2=new Thread(try2);
    t1.start();
    t2.start();
    
}

}
