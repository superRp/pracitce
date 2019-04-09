package test;

public class InterruptedTest {
    public static void main(String[] args) throws InterruptedException {

        Thread t=new Thread(new Runnable() {
            
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(true) {
                    
                    if(Thread.currentThread().isInterrupted()) {
                        System.out.println("current thread is interrupted");
                        break;
                    }else {
                        System.out.println("current thread is going on");
                    }
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                        break;
//                    }
                }
                
            }
        });
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
    
}
