package practice;

public class TestWait {
    final static Object object=new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized(object){
                System.out.println("T1线程开始执行");
                try {
                    System.out.println("T1线程开始等待");
                    object.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("T1线程执行结束");
            }
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized(object) {
                System.out.println("T2线程开始执行");
                System.out.println("T2开始唤醒T1");
                object.notify();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("T2运行结束");
            }
        }
    }
    
    public static void main(String[] args) {
        T1 t1=new T1();
        T2 t2=new T2();
        t1.start();
        t2.start();
    }

}
