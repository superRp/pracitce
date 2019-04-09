package practice;

public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread  t1=new Thread() {
            @Override
            public void run() {
                long time=System.currentTimeMillis();
                // TODO Auto-generated method stub
                while(true) {
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("interrupt");
                        break;
                    }
                    System.out.println("我正常运行");
                    Thread.yield();
                }
                System.out.println("耗时"+(System.currentTimeMillis()-time));
            }
        };
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
    }
}
