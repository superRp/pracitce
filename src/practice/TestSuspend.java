package practice;

public class TestSuspend {
    public static Object u=new Object();
    public static void main(String[] args) throws InterruptedException {
        ChangeObject t1=new ChangeObject("t1");
        ChangeObject t2=new ChangeObject("t2");
        t1.start();
        Thread.sleep(2000);
        System.out.println("------");
        t2.start();
        Thread.sleep(2000);
        t1.resume();
        t2.resume();
        System.out.println("---------");
        t1.join();
        t2.join();

    }
    public static class ChangeObject extends Thread{
        public ChangeObject(String name) {
            super.setName(name);
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized(u) {
                System.out.println("in "+getName());
                System.out.println(getName()+"线程挂起");
                Thread.currentThread().suspend();
                System.out.println(getName()+"执行结束");
            } 
        } 
    } 
}
