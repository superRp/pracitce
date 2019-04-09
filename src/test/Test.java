package test;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread() {
          @Override
            public void run() {
              System.out.println("进入该线程");
                // TODO Auto-generated method stub
                while(true) {
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupt");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        System.out.println("interrupt when sleep");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                    System.out.println("循环末");
                }
                System.out.println("退出线程");
            }  
        };
        Thread t2=new Thread() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("t2执行");
            }
        };
        t.start();
        Thread.sleep(4000);
        t2.start();
        t.interrupt();

    }
}
